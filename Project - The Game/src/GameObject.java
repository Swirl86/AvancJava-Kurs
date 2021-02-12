import java.io.Serializable;
import java.util.Random;

public class GameObject implements Serializable {
    private final String[] availableObjects = {"Healing Potion", "Epic Bag", "Burned Map", "Paper", "Rock", "Scissors",
            "Lamp", "Chair", "Epic Armor", "Legendary Armor", "Transmitter", "Dresser", "Excalibur", "Boomkin Statue",
            "Arcanite Bar", "Mystery Bag", "Translator", "Thunderfury", "Action Potion", "Shadow Oil", "Sulfuras",
            "Recipe: Frost~Oil", "Copper Bar", "Thorium Ore", "Unstable Trigger", "Goblin Mortar", "Linen Bandage"};

    private String object;
    private boolean movable;

    public GameObject() {
        // Random values
        int i = new Random().nextInt(availableObjects.length);
        this.object = availableObjects[i];
        Random rd = new Random();
        this.movable = rd.nextBoolean();
    }

    // Constructor for Container creation
    public GameObject(String object, boolean movable) {
        this.object = object;
        this.movable = movable;
    }

    public String getObjetName() {
        return this.object;
    }

    public void setObjetName(String object) {
        this.object = object;
    }

    public boolean isMovable() {
        return this.movable;
    }

    public String toString() {
        return this.object;
    }


}