import java.util.Arrays;
import java.util.Objects;

public class Game {

    private Gui gui;
    private Update update;
    private Player player;
    private Person[] npcs;
    private Room[] rooms;
    boolean GameIsOn = true;

    public Game() {
        this.gui = new Gui();
        this.player = new Player(5);
        this.npcs = new Person[3];
        this.rooms = new Room[4];
        addNpcs();
        addRooms();

        this.update = new Update(this.gui, this.player, this.npcs, this.rooms, this.GameIsOn);
        Thread thread = new Thread(update);
        thread.start();
        startGame();
    }

    public void addNpcs() {
        this.npcs[0] = new Person("Jason");
        this.npcs[1] = new Person("Freddy");
        this.npcs[2] = new Person("Ture Sventon");
    }

    public void addRooms() {
        for (int i = 0; i < 4; i++) {
            this.rooms[i] = new Room(String.valueOf(i + 1));
        }
        int index = (int) (Math.random() * 3);
        this.player.setRoom(this.rooms[index]);
        this.gui.setShowRoom(this.player.getRoomName() + " " + this.rooms[index].getInventory());
        this.gui.setShowInventory(this.player.getInventory());

        // System.out.println(player.getRoom().getName());
      /*  for (Room room: rooms) {
            System.out.println(room.getName());
        } */

    }

    public void startGame() {
        //ReentrantLock lock = new ReentrantLock();
        long startTime = 0;
        int index = -1;
        int newRoom = -1;
        boolean movable = false;
        boolean exists = false;
        boolean gotSpace = false;
        String[] choice;
        String[] commands;

        while (GameIsOn) {
            startTime = System.currentTimeMillis();
            choice = gui.getCommand().split("\\s", 2);

            //lock.lock();
            switch (choice[0]) {
                //TODO fixa kollarna så allt blir lowercase checkar
                case "Left":
                    index = Integer.parseInt(this.player.getRoomName());
                    newRoom = index - 1;
                    if (checkAdjoiningRoom(index, newRoom)) {
                        this.player.setRoom(this.rooms[newRoom - 1]);
                        updateRoomAndInventoryGui(this.rooms[newRoom - 1]);
                    } else {
                        this.gui.setInfoText("There are no more rooms to the left, go to the right.");
                    }
                    break;
                case "Right":
                    index = Integer.parseInt(this.player.getRoomName());
                    newRoom = index + 1;
                    if (checkAdjoiningRoom(index, newRoom)) {
                        this.player.setRoom(this.rooms[newRoom - 1]);
                        updateRoomAndInventoryGui(this.rooms[newRoom - 1]);
                    } else {
                        this.gui.setInfoText("There are no more rooms to the right, go to the left.");
                    }
                    break;
                case "pickup":
                    commands = splitCommands(choice[1]);
                    //    System.out.println(Arrays.toString(optionBreakdown));
                    index = Integer.parseInt(this.player.getRoomName());

                    movable = this.rooms[index - 1].getInventory().isMovable(commands[0], commands[1]);
                    exists = this.rooms[index - 1].getInventory().itemExists(commands[0], commands[1]);
                    gotSpace = this.player.getInventory().gotSpace();

                    if (movable && exists && gotSpace) {
                        this.player.addToInventory(commands[0]);
                        this.rooms[index - 1].removeItem(commands[0], commands[1]);
                        updateRoomAndInventoryGui(this.rooms[index - 1]);
                    } else {
                        updateInfoTextArea(movable, gotSpace, exists);
                    }
                    break;
                case "drop":
                    commands = splitCommands(choice[1]);
                    // System.out.println(Arrays.toString(optionBreakdown));

                    index = Integer.parseInt(this.player.getRoomName());
                    gotSpace = this.rooms[index - 1].getInventory().gotSpace();
                    exists = this.player.getInventory().itemExists(commands[0], commands[1]);

                    if (gotSpace && exists) {
                        this.player.removeFromInventory(commands[0], commands[1]);
                        this.rooms[index - 1].addItem(commands[0]);
                        updateRoomAndInventoryGui(this.rooms[index - 1]);
                    } else if (!gotSpace) {
                        this.gui.setInfoText("There is no more space in the room. Pickup an item to free up a slot.");
                    } else {
                        this.gui.setInfoText("That item does not exist! Check your spelling or choose another item.");
                    }
                    break;
                case "trade":
                    index = Integer.parseInt(this.player.getRoomName());
                    // TODO fånga upp npc som är i rummet och kolla inventory pausa npc så de inte går

                    break;
                case "open":
                    index = Integer.parseInt(this.player.getRoomName());
                    // TODO öppna container open Chest21 <-- locked
                    // open Chest21 use Key21  <-- not a match / match


                    break;
                default:
                    // TODO varför rullar den på konstant och skriver ut default värdet -_-
                    //this.gui.setInfoText("Not a valid command, try again!");
                    break;
            }
            //lock.unlock();
            // Todo checka om dörren är upplåstdvs spelet klart
            waitForNextInterval(startTime);
        }
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

    private void updateRoomAndInventoryGui(Room room) {
        this.gui.setShowInventory(this.player.getInventory());
        this.gui.setShowRoom(this.player.getRoomName() + ": " + room.getInventory());
    }

    private void updateInfoTextArea(boolean movable, boolean gotSpace, boolean exists) {
        if (!exists) {
            this.gui.setInfoText("That item does not exist! Check your spelling or choose another item.");
        } else if (!gotSpace) {
            this.gui.setInfoText("You don't have any space in your inventory. Drop an item to free up a slot.");
        } else if (!movable) {
            this.gui.setInfoText("You can not pickup that item, it is stuck to the room...");
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
}
