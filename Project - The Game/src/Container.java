import java.util.Random;
import java.util.UUID;

public class Container extends GameObject {
    // chestname array slumpa namn eller uuid unikt id
    // UUID id = UUID.randomUUID(); // lägg in i nyckeln som matchar containern
    // TODO Container21 matchar Key21  <-- randomizera 2 siffrigt nummer

    String containerName;
    Inventory inventory;
    boolean locked;

    public Container(String name) {
        this.containerName = name;
        // TODO fyll med ett item
        this.inventory = new Inventory(1);
        this.inventory.addRandomItem();
        Random rd = new Random();
        this.locked = rd.nextBoolean();
    }

    //lockChest function return key

    @Override
    public String getObjet() {
        return this.containerName;
    }

    @Override
    public String toString() { // show()
        return this.locked ? "Container is NOT locked! Item: " +
                this.inventory.itemToString() : "Container is locked. . .";
    }
}
