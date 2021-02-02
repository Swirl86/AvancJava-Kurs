import java.util.Random;

public class GameObject {
    private final String[] availableObjects = {"Healing Potion", "Epic Bag ", "Burned Map",
            "Key21", "Chair", "Epic Armor", "Legendary Armor", "Dresser", "Excalibur",
            "Arcanite Bar", "Mystery Grab Bag", "Universal Key"};
    private String object;
    private boolean movable;

    public GameObject() {
        int i = new Random().nextInt(availableObjects.length);
        this.object = availableObjects[i];
        Random rd = new Random();
        this.movable = rd.nextBoolean();
    }
    public GameObject(String item) {
        this.object = item;
        this.movable = true;
    }

    public void createObject() {
        int i = new Random().nextInt(availableObjects.length);
        this.object = availableObjects[i];
        Random rd = new Random();
        this.movable = rd.nextBoolean();
    }

    public void setObjet(String item) {
        this.object = item;
    }

    public String getObjet() {
        return this.object;
    }

    public boolean canMove() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public void show(){
        System.out.println("Item: " + this.object + " can be picked up: " + this.movable);
    }

    // TODO lägg till movable
    public String toString(){
        return this.object;
    }


}