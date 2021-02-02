public class Room {
    private String name;
    private int inventorySize;
    private Inventory inventory;
    private boolean isLocked;

    public Room(String name) {
        this.name = name;
        this.inventorySize = getRandomSize() + 10; // Fill With some items then add some empty spaces
        this.inventory = new Inventory(inventorySize);
        this.isLocked = false;

        fillRoomInventory();
    }

    public int getRandomSize() {
        return (int) (Math.random() * 10) + 5;
    }

    public void fillRoomInventory() {
        // TODO lägg till några items i container som är låsta samt tillhörande nycklar
        for (int i = 0; i < this.inventorySize - 10; i++) {
            this.inventory.addRandomItem();
        }
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

    public GameObject getItem(String item) {
        return  this.inventory.getItemFromInventory(item);
    }

    public void removeItem(String item) {
        this.inventory.dropItem(item);
    }

    public void addItem(String item) {
        this.inventory.addItem(item);
    }

   /* public void show() {
        System.out.println("Room: " + this.name + "  " + this.inventory);
    }*/
}
