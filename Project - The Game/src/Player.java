import java.io.Serializable;

public class Player implements Serializable {

    private Room currentRoom;
    private Inventory inventory;

    public Player(int maxSize) {
        this.currentRoom = new Room(0, "Default", "Default");
        this.inventory = new Inventory(maxSize);
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public synchronized Inventory getInventory() {
        return this.inventory;
    }

    /* Methods to shorten code rows when fetching values for Game / Update */
    public synchronized int getRoomIndex() {
        return this.currentRoom.getIndex();
    }

    public Container getContainer(String container) {
        return this.inventory.getContainer(container);
    }

    public boolean gotSpace() {
        return this.inventory.gotSpace();
    }

    public void addGameObject(GameObject object) {
        this.inventory.addGameObject(object);
    }

    public void dropGameObject(GameObject object) {
        this.inventory.dropGameObject(object);
    }

}
