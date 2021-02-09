import java.io.Serializable;
import java.util.Random;

public class GameObject implements Serializable {
    private final String[] availableObjects = {"Healing Potion", "Epic Bag", "Burned Map", "Paper",
            "Lamp", "Chair", "Epic Armor", "Legendary Armor", "Transmitter", "Dresser", "Excalibur",
            "Arcanite Bar", "Mystery Bag", "Translator"};

    private String object;
    private boolean movable;

    public GameObject() {
        // Random values
        int i = new Random().nextInt(availableObjects.length);
        this.object = availableObjects[i];
        Random rd = new Random();
        this.movable = rd.nextBoolean();
    }

    // After picking up an item and drop it again. Because you drop it it is movable
    public GameObject(String object) {
        this.object = object;
        this.movable = true;
    }

    // Constructor for Container creation
    public GameObject(String object, boolean movable) {
        this.object = object;
        this.movable = movable;
    }

    public String getObjetName() {
        return this.object;
    }

    public boolean isMovable() {
        return this.movable;
    }

    public String toString() {
        return this.object;
    }


}