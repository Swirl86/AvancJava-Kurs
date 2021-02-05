public class Player {

    private Room currentRoom;
    private Inventory inventory;

    public Player(int maxSize) {
        this.currentRoom = new Room("Default");
        this.inventory = new Inventory(maxSize);
    }
    public Player(Room room, Inventory inventory) {
        this.currentRoom = room;
        this.inventory = inventory;
    }

    public Room getRoom() {
        return this.currentRoom;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public String getRoomName() {
        return currentRoom.getName();
    }

    public void setRoom(Room room) {
        this.currentRoom = room;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addToInventory(String item) {
        this.inventory.addItem(item);
    }

   /* public void removeFromInventory(String item) {
        this.inventory.dropItem(item);
    }
*/
   public void removeFromInventory(String item, String pos) {
       this.inventory.dropItem(item, pos);
   }
}
