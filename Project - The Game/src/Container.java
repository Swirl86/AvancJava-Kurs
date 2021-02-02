import java.util.Random;

public class Container extends GameObject {

    Inventory inventory;
    boolean locked;

    public Container() {
        this.inventory = new Inventory(2);
        Random rd = new Random();
        this.locked = rd.nextBoolean();
    }

    @Override
    public void show() {
        System.out.println("Container is locked: " + this.locked);
    }
}
