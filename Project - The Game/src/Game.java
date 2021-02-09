import java.io.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game implements Serializable {
    private final String[] roomName = {"Sunny Side",
            "Dark Side", "Foul corner",
            "Kawaii cafe"};
    private final String[] roomDescription = {"Bright room with 1 written on the door.",
            "Dark room with 2 written on the door.", "Foul room with 3 written on the door.",
            "Kawaii room with 4 written on the door."};

    private String filenamn = "TheGame";

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

    private void startThreads() {
        this.pool = new ScheduledThreadPoolExecutor(10);
        this.pool.scheduleAtFixedRate(this.updateNpc1, 0, 15, TimeUnit.SECONDS);
        this.pool.scheduleAtFixedRate(this.updateNpc2, 8, 15, TimeUnit.SECONDS);
        this.pool.scheduleAtFixedRate(this.updateNpc3, 15, 15, TimeUnit.SECONDS);
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

    private void addNpcsToRooms() {
        for (Room room : this.rooms) {
            for (Person npc : this.npcs) {
                if (npc.getPosition() == (room.getIndex() - 1)) {
                    room.addPerson(npc);
                }
            }
        }
    }

    public void startGame() {
        //ReentrantLock lock = new ReentrantLock();
        // Reoccurring variables
        long startTime;
        int index;
        int newRoom;
        boolean movable;
        boolean exists;
        boolean gotSpace;
        String object;
        String position;
        String[] choice;
        String[] commands;
        GameObject item;
        Container container;
        Key key;

        while (this.GameIsOn) {
            startTime = System.currentTimeMillis(); // Slow down for printouts
            choice = this.gui.getCommand().split("\\s", 2);

            //lock.lock();
            switch (choice[0]) {
                //TODO Add only lowercase checks
                case "Left":
                    index = this.player.getRoomIndex();
                    newRoom = index - 1;
                    if (checkAdjoiningRoom(index, newRoom)) {
                        this.player.setCurrentRoom(this.rooms[newRoom - 1]);
                        updateRoomAndInventoryGui(this.rooms[newRoom - 1]);
                    } else {
                        this.gui.setInfoText("\n\nThere are no more rooms to the left, go to the right.");
                    }
                    break;
                case "Right":
                    index = this.player.getRoomIndex();
                    newRoom = index + 1;
                    if (checkAdjoiningRoom(index, newRoom)) {
                        this.player.setCurrentRoom(this.rooms[newRoom - 1]);
                        updateRoomAndInventoryGui(this.rooms[newRoom - 1]);
                    } else {
                        this.gui.setInfoText("\n\nThere are no more rooms to the right, go to the left.");
                    }
                    break;
                case "pickup":
                    commands = splitCommands(choice[1]);
                    index = this.player.getRoomIndex();
                    object = commands[0];
                    position = commands[1];

                    movable = this.rooms[index - 1].getInventory().isMovable(object, position);
                    exists = this.rooms[index - 1].getInventory().itemExists(object, position);
                    gotSpace = this.player.gotSpace();

                    if (movable && exists && gotSpace) {
                        item = this.rooms[index - 1].getInventory().getGameObject(object, position);
                        this.player.addItem(item);
                        this.rooms[index - 1].getInventory().dropGameObject(item);
                        updateRoomAndInventoryGui(this.rooms[index - 1]);
                    } else {
                        updateInfoTextArea(movable, gotSpace, exists, false);
                    }
                    break;
                case "drop":
                    commands = splitCommands(choice[1]);
                    index = this.player.getRoomIndex();
                    object = commands[0];
                    position = commands[1];
                    gotSpace = this.rooms[index - 1].gotSpace();
                    exists = this.player.getInventory().itemExists(object, position);

                    if (gotSpace && exists) {
                        item = this.player.getInventory().getGameObject(object, position);
                        this.player.dropGameObject(item);
                        this.rooms[index - 1].addItem(item);
                        updateRoomAndInventoryGui(this.rooms[index - 1]);
                    } else if (!gotSpace) {
                        this.gui.setInfoText("There is no more space in the room. Pickup an item to free up a slot.");
                    } else {
                        this.gui.setInfoText("That item does not exist! Check your spelling or choose another item.");
                    }
                    break;
                case "open":
                    commands = splitCommandsContainer(choice[1]);
                    index = this.player.getRoomIndex();
                    // Is the container in the inventory, room or null
                    container = checkContainer(index, commands[0]);

                    if (container == null) {
                        this.gui.setInfoText("That item does not exist! Check your spelling or choose another item.");
                    } else {
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
                    break;
                case "use":
                    commands = splitCommandsContainer(choice[1]);
                    index = this.player.getRoomIndex();
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
                                        "use Teleporter KeyCard");
                            }
                        }
                    }
                    break;
                case "trade":
                    index = this.player.getRoomIndex();
                    // TODO fånga upp npc som är i rummet och kolla inventory pausa npc så de inte går
                    System.out.println("TRADE");
                    break;
            }
            //lock.unlock();
            waitForNextInterval(startTime); // Slow down for printouts
        }
        this.pool.shutdownNow();
        this.gui.setInfoText("\n\n      VICTORY! You have successfully teleported out of the game area!");
    }


    private void openContainer(int index, Container container) {
        boolean movable = container.getInventory().containerObjectIsMovable();
        boolean gotSpace = this.player.gotSpace();

        if (movable && gotSpace) {
            GameObject item = container.getInventory().getContainerGameObject();
            this.player.getInventory().addItem(item);
            // TODO add new message that the container is empty instead of deleting it
            this.rooms[index - 1].dropGameObject(item);
            updateRoomAndInventoryGui(this.rooms[index - 1]);
        } else {
            updateInfoTextArea(movable, gotSpace, true, true);
        }
    }

    private Container checkContainer(int index, String containerName) {
        Container container = this.rooms[index - 1].getContainer(containerName);
        if (container == null) {
            container = this.player.getContainer(containerName);
        }
        return container;
    }

    private String[] splitCommands(String commands) {
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

    private String[] splitCommandsContainer(String commands) {
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

    private void updateRoomAndInventoryGui(Room room) {
        this.gui.setShowInventory(this.player.getInventory());
        this.gui.setShowRoom(room.toString());
    }

    private void updateInfoTextArea(boolean movable, boolean gotSpace, boolean exists, boolean isContainer) {
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

    private boolean checkAdjoiningRoom(int currentRoom, int newRoom) {
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

    private void waitForNextInterval(long startTime) {
        long currentTime = System.currentTimeMillis();
        long endTime = startTime + 1000;
        long delta = endTime - currentTime;
        if (delta > 0) {
            try {
                Thread.sleep(delta);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public Game load() {
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
            FileOutputStream fos = new FileOutputStream(this.filenamn);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
