import java.io.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game implements Serializable {
    private final String[] roomName = {"Sunny Side", "Dark Side", "Foul corner", "Kawaii cafe"};
    private final String[] roomDescription = {"Bright room with 1 written on the door.",
            "Dark room with 2 written on the door.", "Foul room with 3 written on the door.",
            "Kawaii room with 4 written on the door."};

    private String filenamn = "TheGame.txt";

    private Gui gui;
    private Update updateNpc1;
    private Update updateNpc2;
    private Update updateNpc3;
    private Player player;
    private Person[] npcs;
    private Room[] rooms;
    boolean GameIsOn = true;
    ScheduledThreadPoolExecutor pool;


    public Game() {
        this.gui = new Gui();
        this.player = new Player(5);
        this.npcs = new Person[3];
        this.rooms = new Room[4];
        addNpcs();
        addRooms();

        this.updateNpc1 = new Update(this.gui, this.player, this.npcs[0], this.rooms, this.GameIsOn);
        this.updateNpc2 = new Update(this.gui, this.player, this.npcs[1], this.rooms, this.GameIsOn);
        this.updateNpc3 = new Update(this.gui, this.player, this.npcs[2], this.rooms, this.GameIsOn);
        //updateRoomAndInventoryGui(this.rooms[this.player.getRoomIndex() - 1]);

        startThreads();
        startGame();
    }

    public void startThreads() {
        this.pool = new ScheduledThreadPoolExecutor(10);
        this.pool.scheduleAtFixedRate(this.updateNpc1, 0, 15, TimeUnit.SECONDS);
        this.pool.scheduleAtFixedRate(this.updateNpc2, 5, 15, TimeUnit.SECONDS);
        this.pool.scheduleAtFixedRate(this.updateNpc3, 10, 15, TimeUnit.SECONDS);
    }

    public void addNpcs() {
        this.npcs[0] = new Person("Jason Voorhees", getRandomNumber());
        this.npcs[1] = new Person("Freddy Krueger", getRandomNumber());
        this.npcs[2] = new Person("Ture Svensson", getRandomNumber());
    }

    public void addRooms() {
        for (int i = 0; i < 4; i++) {
            this.rooms[i] = new Room((i + 1), roomName[i], roomDescription[i]);
        }

        int index = (int) (Math.random() * 4);
        this.player.setCurrentRoom(this.rooms[index]);
        Room room = this.player.getCurrentRoom();
        this.gui.setShowRoom(room.toString());
        this.gui.setShowInventory(this.player.getInventory());
        this.rooms[3].addTeleport(); // End game object
        addNpcsToRooms();
    }

    public void addNpcsToRooms() {
        for (Room room : this.rooms) {
            for (Person npc : this.npcs) {
                if (npc.getPosition() == (room.getIndex() - 1)) {
                    room.addPerson(npc);
                }
            }
        }
    }

    public void startGame() {
        // Reoccurring variables
        long startTime;
        int index;
        int newRoom;
        String object;
        String position;
        String[] choice;
        String[] commands;
        Container container;
        Key key;

        while (this.GameIsOn) {
            choice = this.gui.getCommand().split("\\s", 2);

            switch (choice[0]) {
                //TODO Add only lowercase checks
                case "Left":
                    index = getIndex();
                    newRoom = index - 1;
                    if (checkAdjoiningRoom(index, newRoom)) {
                        updatePlayerCurrentRoom(this.rooms[newRoom - 1]);
                    } else {
                        this.gui.setInfoText("\n\nThere are no more rooms to the left, go to the right.");
                    }
                    break;
                case "Right":
                    index = getIndex();
                    newRoom = index + 1;
                    if (checkAdjoiningRoom(index, newRoom)) {
                        updatePlayerCurrentRoom(this.rooms[newRoom - 1]);
                    } else {
                        this.gui.setInfoText("\n\nThere are no more rooms to the right, go to the left.");
                    }
                    break;
                case "pickup":
                    try {
                        commands = splitCommands(choice[1]);
                        index = getIndex();
                        object = commands[0];
                        position = commands[1];

                        pickUpAction(index, object, position);
                    } catch (Exception e) {
                        this.gui.setInfoText("\nCheck you input!\n" +
                                "To pickup an item an item there need to be a free space in your inventory.\n" +
                                "Command to pickup an item: pickup item  or  pickup item 2");
                    }
                    break;
                case "drop":
                    try {
                        commands = splitCommands(choice[1]);
                        index = getIndex();
                        object = commands[0];
                        position = commands[1];

                        dropAction(index, object, position);
                    } catch (Exception e) {
                        this.gui.setInfoText("\nCheck you input!\n" +
                                "To drop an item an item there need to be a free space in the room.\n" +
                                "Command to drop an item: drop item  or  drop item 2");
                    }
                    break;
                case "open":
                    try {
                        commands = splitCommandsContainer(choice[1]);
                        index = getIndex();
                        // Is the container in the inventory, room or null
                        container = checkContainer(index, commands[0]);

                        if (container == null) {
                            this.gui.setInfoText("That item does not exist! Check your spelling or choose another item.");
                        } else {
                            testContainerKey(index, commands, container);
                        }
                    } catch (Exception e) {
                        this.gui.setInfoText("\nCheck you input!\n" +
                                "To open a container you need to have the Key in you inventory.\n" +
                                "Command to open the container: open Container Key");
                    }
                    break;
                case "use":
                    try {
                        commands = splitCommandsContainer(choice[1]);
                        index = getIndex();
                        // Is the container in the inventory, room or null
                        container = checkContainer(index, commands[0]);
                        if (container == null) {
                            this.gui.setInfoText("That item does not exist! Check your spelling or choose another item.");
                        } else {
                            if (container.isLocked()) {
                                key = this.player.getInventory().getKey(commands[1].trim());
                                if (key != null && key.fit(container)) {
                                    // Got the KeyCard in the inventory and the command for use with KeyCard has been given
                                    openContainer(index, container);
                                    this.GameIsOn = false; // Left the game room Victory!
                                } else {
                                    // Did not give the command to use KeyCard
                                    this.gui.setInfoText("\nYou can not use the teleporter! You need the KeyCard to " +
                                            "activate emergency teleport protocol.\n" +
                                            "Find and pickup the KeyCard then enter the following command:\n" +
                                            "       use Teleporter KeyCard");
                                }
                            }
                        }
                    } catch (Exception e) {
                        this.gui.setInfoText("\nCheck you input!\n" +
                                "To use the teleport you need to have the KeyCard in you inventory.\n" +
                                "Command for use: use Teleporter KeyCard");
                    }
                    break;
                case "trade":
                    try {
                        commands = splitCommands(choice[1]);
                        position = commands[1];
                        tradeWithNpc(position);
                    } catch (Exception e) {
                        this.gui.setInfoText("\nCheck you input!\n" +
                                "To trade you need to enter trade and position of the npc you want to trade with.\n" +
                                "Command for trade: trade 1");
                    }
                    break;
            }
        }
        this.pool.shutdownNow();
        this.gui.setInfoText("\n\n      VICTORY! You have successfully teleported out of the game area!");
    }

    public void updateRoomAndInventoryGui(Room room) {
        this.updateNpc1.updateInformationOnGui(room);
        this.updateNpc2.updateInformationOnGui(room);
        this.updateNpc3.updateInformationOnGui(room);
    }

    public void updatePlayerCurrentRoom(Room room) {
        synchronized (this) {
            this.player.setCurrentRoom(room);
        }
        updateRoomAndInventoryGui(room);
    }

    public synchronized int getIndex() {
        return this.player.getRoomIndex();
    }

    public synchronized void dropAction(int index, String object, String position) {
        boolean gotSpace = this.rooms[index - 1].gotSpace();
        boolean exists = this.player.getInventory().itemExists(object, position);

        if (gotSpace && exists) {
            GameObject item = this.player.getInventory().getGameObject(object, position);
            this.player.dropGameObject(item);
            this.rooms[index - 1].addItem(item);
            updateRoomAndInventoryGui(this.rooms[index - 1]);
        } else if (!gotSpace) {
            this.gui.setInfoText("There is no more space in the room. Pickup an item to free up a slot.");
        } else {
            this.gui.setInfoText("That item does not exist! Check your spelling or choose another item.");
        }
    }

    public synchronized void pickUpAction(int index, String object, String position) {
        boolean movable = this.rooms[index - 1].getInventory().isMovable(object, position);
        boolean exists = this.rooms[index - 1].getInventory().itemExists(object, position);

        boolean gotSpace = this.player.gotSpace();

        if (movable && exists && gotSpace) {
            GameObject item = this.rooms[index - 1].getInventory().getGameObject(object, position);
            this.player.addGameObject(item);
            this.rooms[index - 1].getInventory().dropGameObject(item);
            updateRoomAndInventoryGui(this.rooms[index - 1]);
        } else {
            updateInfoTextArea(movable, gotSpace, exists, false);
        }
    }

    public void testContainerKey(int index, String[] commands, Container container) {
        Key key;
        if (container.isLocked()) {
            key = this.player.getInventory().getKey(commands[1].trim());
            if (key != null && key.fit(container)) {
                // Got the Key in the inventory and the command for open with key has been given
                openContainer(index, container);
            } else {
                // Did not give the command for open with key
                this.gui.setInfoText("\nThe Container is locked, find the matching Key an pick it up.\n" +
                        "You need to have they key in you inventory to use it!\n" +
                        "Unlock the container by entering the command: open container key");
            }
        } else {
            // Not locked pickup the item if possible look at pickup case
            openContainer(index, container);
        }
    }

    public void openContainer(int index, Container container) {
        boolean movable = container.getInventory().containerObjectIsMovable(); // always an item in a container
        boolean gotSpace = this.player.gotSpace();

        if (movable && gotSpace) {
            GameObject item = container.getInventory().getFirstGameObject();
            this.player.getInventory().addGameObject(item);
            // TODO add new message that the container is empty instead of deleting it
            this.rooms[index - 1].dropGameObject(item);
            updateRoomAndInventoryGui(this.rooms[index - 1]);
        } else {
            updateInfoTextArea(movable, gotSpace, true, true);
        }
    }

    public Container checkContainer(int index, String containerName) {
        Container container = this.rooms[index - 1].getContainer(containerName);
        if (container == null) {
            container = this.player.getContainer(containerName);
        }
        return container;
    }

    public synchronized void tradeWithNpc(String position) {
        try {
            int pos = Integer.parseInt(position) - 1;
            Person[] persons = this.rooms[this.player.getRoomIndex() - 1].getPersonsFromRoom();
            if (persons[pos].getInventory().getNumberOfItems() != 0) {
                GameObject itemNpc = persons[pos].getInventory().getFirstGameObject();
                GameObject itemPlayer = this.player.getInventory().getFirstGameObject();
                // TODO add boolean top randomize if npc wants to trade or not
                if (itemNpc != null && itemPlayer != null && !persons[pos].getName().equals("Ghost-Bob")) {
                    this.player.addGameObject(itemNpc);
                    persons[pos].dropGameObject(itemNpc);
                    persons[pos].getInventory().addGameObject(itemPlayer);
                    this.player.dropGameObject(itemPlayer);
                    updatePlayerCurrentRoom(this.player.getCurrentRoom());
                } else {
                    this.gui.setInfoText("\n" + persons[pos].getName() +
                            " does not want to trade! Maybe you're not carrying anything?" +
                            "\n\nOhh . . Also you can not trade with Ghost-Bob . .  He is a ghost!");
                }
            } else {
                this.gui.setInfoText("\n" + persons[pos].getName() + " don't have a item in their inventory!");
            }
        } catch (NumberFormatException e) {
            this.gui.setInfoText("\nNpc position must be a number. . .");
        }
    }

    public String[] splitCommands(String commands) {
        String[] returnValues = {"", ""};
        String[] options = commands.split("\\s", 4);

        if (options[options.length - 1].matches("[0-9]+")) {
            // Last input is position for item
            for (int i = 0; i < options.length - 1; i++) {
                returnValues[0] += options[i] + " ";
            }
            returnValues[1] = options[options.length - 1];
        } else {
            // create split items to one string
            for (String str : options) {
                returnValues[0] += str + " ";
            }
            returnValues[1] = "0";
        }
        returnValues[0] = returnValues[0].trim();

        return returnValues;
    }

    public String[] splitCommandsContainer(String commands) {
        String[] returnValues = {"", ""};
        String[] options = commands.split("\\s", 3);
        if (options.length == 2) {
            returnValues[0] = options[0].trim(); // Container
            returnValues[1] = options[1].trim(); // Key
        } else {
            returnValues[0] = options[0].trim(); // Container
            returnValues[1] = "noName"; // Key
        }
        return returnValues;
    }

    public void updateInfoTextArea(boolean movable, boolean gotSpace, boolean exists, boolean isContainer) {
        if (!exists) {
            this.gui.setInfoText("That item does not exist! Check your spelling or choose another item.");
        } else if (!gotSpace) {
            this.gui.setInfoText("You don't have any space in your inventory. Drop an item to free up a slot.");
        } else if (isContainer) {
            this.gui.setInfoText("You can not open the Container, it is stuck . . .");
        } else if (!movable) {
            this.gui.setInfoText("You can not pickup that item, it is stuck . . .");
        }
    }

    public boolean checkAdjoiningRoom(int currentRoom, int newRoom) {
        boolean validRoomChange = false;
        if (currentRoom == 1 && newRoom == 2) {
            validRoomChange = true;
        } else if (currentRoom == 2 && newRoom == 1 || currentRoom == 2 && newRoom == 3) {
            validRoomChange = true;
        } else if (currentRoom == 3 && newRoom == 2 || currentRoom == 3 && newRoom == 4) {
            validRoomChange = true;
        } else if (currentRoom == 4 && newRoom == 3) {
            validRoomChange = true;
        }
        return validRoomChange;
    }

    public int getRandomNumber() {
        return (int) (Math.random() * 4);
    }

   /* public Game load() {
        Game game = this;
        try {
            FileInputStream fis = new FileInputStream(this.filenamn);
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return game;
    }

    public void save() {
        try {
            FileOutputStream fout = new FileOutputStream(this.filenamn);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
