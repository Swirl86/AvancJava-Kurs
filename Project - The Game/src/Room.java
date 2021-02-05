public class Room {
    private String name;
    private int inventorySize;
    private Inventory inventory;
    private boolean isLocked;

    public Room(String name) {
        this.name = name;
        this.inventorySize = getRandomSize() + 10;
        this.inventory = new Inventory(inventorySize);
        this.isLocked = false;
        fillRoomInventory();
    }

    // To many items in a room, 5-15?
    public int getRandomSize() {
        return (int) (Math.random() * 10) + 5;
    }

    public void fillRoomInventory() {
        // Fill With some items and leave some empty spaces
        for (int i = 0; i < this.inventorySize - 10; i++) {
            this.inventory.addRandomItem();
        }
        // TODO randomizera 1-3 kistor i varje rum
        // this.inventory.createContainer(); // <-- testa
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void removeItem(String item, String pos) {
        this.inventory.dropItem(item, pos);
    }

    public void addItem(String item) {
        this.inventory.addItem(item);
    }

   /* public GameObject getItem(String item) {
        return this.inventory.getItemFromInventory(item);
    }

     public boolean isMovable(String item) {
        return this.inventory.isMovable(item);
    }

   public void show() {
        System.out.println("Room: " + this.name + "  " + this.inventory);
    }*/
}
