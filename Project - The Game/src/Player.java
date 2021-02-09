import java.io.Serializable;

public class Player implements Serializable {

    private Room currentRoom;
    private Inventory inventory;

    public Player(int maxSize) {
        this.currentRoom = new Room(0, "Default", "Default");
        this.inventory = new Inventory(maxSize);
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public int getRoomIndex() {
        return this.currentRoom.getIndex();
    }

    public Container getContainer(String container) {
        return this.inventory.getContainer(container);
    }

    /* Shorter rows when fetching inventory values */
    public boolean gotSpace() {
        return this.inventory.gotSpace();
    }

    public void addItem(GameObject object) {
        this.inventory.addItem(object);
    }

    public void dropGameObject(GameObject object) {
        this.inventory.dropGameObject(object);
    }

}
