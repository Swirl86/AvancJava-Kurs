import java.io.Serializable;

public class Container extends GameObject implements Serializable {

    private Inventory inventory;
    private boolean locked;

    public Container(String name, boolean movable, boolean locked) {
        super(name, movable);
        this.inventory = new Inventory(1);
        this.inventory.addRandomItem();
        this.locked = locked;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public String toString() {
        return this.inventory.itemToString();
    }
    
}
