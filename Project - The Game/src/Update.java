public class Update implements Runnable {
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
        int action = getRandomNumber();
        int npcPos = -1;
        switch (action) {
            case 0:
                // Drop item if space
                dropItem();
                break;
            case 1:
                // pickup item if space
                pickUpItem();
                break;
            case 2:
                // Go left
                npcPos = this.npc.getPosition();
                moveNpcToNewRoom(npcPos, (npcPos - 1));
                break;
            case 3:
                // Go Right
                npcPos = this.npc.getPosition();
                moveNpcToNewRoom(npcPos, (npcPos + 1));

                break;
        }
    }

    public synchronized void moveNpcToNewRoom(int npcPos, int newPos) {
        if (checkAdjoiningRoom(npcPos, newPos)) {
            this.rooms[npcPos].removePerson(this.npc);
            this.npc.setPosition(newPos);
            this.rooms[newPos].addPerson(this.npc);
            updateInformationOnGui(this.rooms[this.currentRoom]);
        }
    }

    public synchronized void dropItem() {
        int npcPos = this.npc.getPosition();
        boolean gotSpace = this.rooms[npcPos].gotSpace();
        boolean gotItem = this.npc.getNumberOfItems() != 0;

        if (gotSpace && gotItem) {
            GameObject item = this.npc.getInventory().getFirstGameObject();
            this.npc.dropGameObject(item);
            this.rooms[npcPos].addGameObject(item);
            updateInformationOnGui(this.rooms[this.currentRoom]);
        }
    }

    public synchronized void pickUpItem() {
        int npcPos = this.npc.getPosition();
        boolean gotSpace = this.npc.getNumberOfItems() == 0;
        boolean movable = this.rooms[npcPos].getInventory().getRandomGameObject().isMovable(); // Nullpoint

        if (gotSpace && movable) {
            GameObject item = this.rooms[npcPos].getInventory().getRandomGameObject();
            npc.getInventory().addGameObject(item);
            this.rooms[npcPos].dropGameObject(item);
            updateInformationOnGui(this.rooms[this.currentRoom]);
        }
    }


    public void updateInformationOnGui(Room room) {
        updateCurrentRoom();
        if ((room.getIndex() - 1) == this.currentRoom) {
            updateNpc();
            this.gui.setShowInventory(this.player.getInventory());
            this.gui.setShowRoom(room.toString());
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
        return (int) (Math.random() * 6);
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
}
