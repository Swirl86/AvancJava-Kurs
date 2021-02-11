import java.io.Serializable;

public class Update implements Runnable, Serializable {
    private Gui gui;
    private Player player;
    private Person npc;
    private Room[] rooms;
    private int currentRoom;
    boolean GameIsOn;

    public Update(Gui gui, Player player, Person npc, Room[] rooms, boolean gameIsOn) {
        this.gui = gui;
        this.player = player;
        this.npc = npc;
        this.rooms = rooms;
        this.currentRoom = this.player.getRoomIndex() - 1;
        this.GameIsOn = gameIsOn;

        updateInformationOnGui(this.rooms[this.currentRoom]);
    }

    @Override
    public void run() {
        try {
            int action = getRandomNumber();

            switch (action) {
                case 0:
                    // Drop item if space
                    actionItem("drop");
                    // dropItem();
                    break;
                case 1:
                    // pickup item if space
                    actionItem("pickup");
                    //  pickUpItem();
                    break;
                case 2:
                    // Go left
                    moveAction("left");
                    //sleep(5000);
                    break;
                case 3:
                    // Go Right
                    moveAction("right");
                    break;
                default:
                    // Hang around in the room
                    System.out.println(this.npc.name + " Hanging around . .  ");
                    sleep(5000);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            // e.printStackTrace();
        }
    }

    public void moveAction(String action) {
        int npcPos;
        switch (action) {
            case "left":
                npcPos = this.npc.getPosition();
                moveNpcToNewRoom(npcPos, (npcPos - 1));
                break;
            case "right":
            default:
                npcPos = this.npc.getPosition();
                moveNpcToNewRoom(npcPos, (npcPos + 1));
                break;
        }
    }

    public void moveNpcToNewRoom(int npcPos, int newPos) {
        if (checkAdjoiningRoom(npcPos, newPos)) {
            synchronized (this) {
                this.rooms[npcPos].removePerson(this.npc);
                this.npc.setPosition(newPos);
                this.rooms[newPos].addPerson(this.npc);
                updateInformationOnGui(this.rooms[this.currentRoom]);
                System.out.println(this.npc.name + " Changed room  " + (npc.getPosition() + 1));
            }
        }
    }

    public synchronized void actionItem(String action) {
        switch (action) {
            case "pickup":
                pickUpItem();
                break;
            case "drop":
            default:
                dropItem();
                break;
        }
        //updateInformationOnGui(this.rooms[this.currentRoom]);
    }

    public void dropItem() {
        int npcPos = this.npc.getPosition();
        boolean gotSpace = this.rooms[npcPos].gotSpace();
        boolean gotItem = this.npc.getNumberOfItems() != 0;

        if (gotSpace && gotItem) {
            GameObject item = this.npc.getInventory().getFirstGameObject();
            this.npc.dropGameObject(item);
            this.rooms[npcPos].getInventory().addGameObject(item);
            updateInformationOnGui(this.rooms[this.currentRoom]);
            System.out.println(this.npc.name + " NPC DROP  " + item + "  in room " +
                    (this.npc.getPosition() + 1));
        }
    }

    public void pickUpItem() {
        int npcPos = this.npc.getPosition();
        boolean gotSpace = this.npc.getNumberOfItems() == 0;
        boolean movable = this.rooms[npcPos].getInventory().getRandomGameObject().isMovable(); // Nullpoint

        if (gotSpace && movable) {
            GameObject item = this.rooms[npcPos].getInventory().getRandomGameObject();
            npc.getInventory().addGameObject(item);
            this.rooms[npcPos].getInventory().dropGameObject(item);
            updateInformationOnGui(this.rooms[this.currentRoom]);
            System.out.println(this.npc.name + " NPC PICKUP " + item + " in room " +
                    (this.npc.getPosition() + 1));
        }
    }


    public void updateInformationOnGui(Room room) {
        updateCurrentRoom();
        if ((room.getIndex() - 1) == this.currentRoom) {
            synchronized (this) {
                updateNpc();
                this.gui.setShowInventory(this.player.getInventory());
                this.gui.setShowRoom(room.toString());
            }
        }
    }

    public void updateCurrentRoom() {
        this.currentRoom = this.player.getRoomIndex() - 1;
    }

    public void updateNpc() {
        // Get all Npc in same room as player
        this.gui.setShowPersons(this.rooms[this.currentRoom].getPersonsFromRoom());
    }

    public int getRandomNumber() {
        int value = (int) (Math.random() * 6);
        System.out.println(value + "  " + this.npc.getName());
        return value;
    }

    public boolean checkAdjoiningRoom(int currentRoom, int newRoom) {
        boolean validRoomChange = false;
        if (currentRoom == 0 && newRoom == 1) {
            validRoomChange = true;
        } else if (currentRoom == 1 && newRoom == 0 || currentRoom == 1 && newRoom == 2) {
            validRoomChange = true;
        } else if (currentRoom == 2 && newRoom == 1 || currentRoom == 2 && newRoom == 3) {
            validRoomChange = true;
        } else if (currentRoom == 3 && newRoom == 2) {
            validRoomChange = true;
        }

        return validRoomChange;
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
