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
    }

    @Override
    public void run() {
        //  System.out.println(Thread.currentThread().getName() + " is running!");
        updateInformationOnGui(this.rooms[this.currentRoom]);
        int npcPos = -1;
        int newPos = -1;
        int action = getRandomNumber();

        switch (action) {
            case 0:
                // Drop item if space
                actionItem("drop");
                break;
            case 1:
                // pickup item if space
                actionItem("pickup");
                break;
            case 2:
                // Go left
                npcPos = this.npc.getPosition();
                newPos = npcPos - 1;
                moveNpcToNewRoom(npcPos, newPos);
                //sleep(5000);
                break;
            case 3:
                // Go Right
                npcPos = npc.getPosition();
                newPos = npcPos + 1;
                moveNpcToNewRoom(npcPos, newPos);
                break;
            default:
                // Hang around in the room
                System.out.println(this.npc.name + " Hanging around . .  ");
                sleep(5000);
                break;
        }
        //updateInformationOnGui(this.rooms[this.currentRoom]);
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
        updateInformationOnGui(this.rooms[this.currentRoom]);
    }

    public void dropItem() {
        int npcPos = this.npc.getPosition();
        boolean gotSpace = this.rooms[npcPos].gotSpace();
        boolean gotItem = this.npc.getNumberOfItems() != 0;
        if (gotSpace && gotItem) {
            // TODO hämta på random position
            GameObject item = this.npc.getInventory().getFirstGameObject();
            this.npc.dropGameObject(item);
            this.rooms[npcPos].getInventory().addItem(item);
            //updateInformationOnGui(this.rooms[this.currentRoom]);
            System.out.println(this.npc.name + " NPC DROP  " + item + "  in room " +
                    (this.npc.getPosition() + 1));
        }
    }

    public void pickUpItem() {
        int npcPos = this.npc.getPosition();
        boolean gotSpace = this.npc.getNumberOfItems() == 0;
        // TODO hämta på random position
        boolean movable = this.rooms[npcPos].getInventory().getFirstGameObject().isMovable();
        if (gotSpace && movable) {
            GameObject item = this.rooms[npcPos].getInventory().getFirstGameObject();
            npc.getInventory().addItem(item);
            this.rooms[npcPos].getInventory().dropGameObject(item);
            // updateInformationOnGui(this.rooms[this.currentRoom]);
            System.out.println(this.npc.name + " NPC PICKUP " + item + " in room " +
                    (this.npc.getPosition() + 1));
        }
    }

    public void moveNpcToNewRoom(int npcPos, int newPos) {
        if (checkAdjoiningRoom(npcPos, newPos)) {
            this.rooms[npcPos].removePerson(this.npc);
            this.npc.setPosition(newPos);
            this.rooms[newPos].addPerson(this.npc);
            updateInformationOnGui(this.rooms[this.currentRoom]);
            System.out.println(this.npc.name + " Changed room  " + (npc.getPosition() + 1));
        }
    }

    public synchronized void updateInformationOnGui(Room room) {
        updateCurrentRoom();
        updateNpc();
        sleep(100);
        if ((room.getIndex() - 1) == this.currentRoom) {
            this.gui.setShowInventory(this.player.getInventory());
            this.gui.setShowRoom(room.toString());
        }

    }

    public void updateCurrentRoom() {
        this.currentRoom = this.player.getRoomIndex() - 1;
    }

    private void updateNpc() {
        this.gui.setShowPersons(this.rooms[this.currentRoom].getPersonsFromRoom());
    }

    public int getRandomNumber() {
        int value = (int) (Math.random() * 6);
        System.out.println(value + "  " + this.npc.getName());
        return value;
    }

    private boolean checkAdjoiningRoom(int currentRoom, int newRoom) {
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
