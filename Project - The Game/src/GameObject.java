import java.util.Random;

public class GameObject {
    // TODO Ta bort nycklarna förutom universal
    private final String[] availableObjects = {"Healing Potion", "Epic Bag", "Burned Map", "Key76",
            "Key21", "Chair", "Epic Armor", "Legendary Armor", "Key04", "Dresser", "Excalibur",
            "Arcanite Bar", "Mystery Grab Bag", "Universal Key"};

    private String object;
    private boolean movable;

    public GameObject() {
        int i = new Random().nextInt(availableObjects.length);
        this.object = availableObjects[i];
        Random rd = new Random();
        this.movable = rd.nextBoolean();
    }

    // After picking up an item and drop it again
    // Because you drop it it is movable
    public GameObject(String item) {
        this.object = item;
        this.movable = true;
    }

    public void setObjet(String item) {
        this.object = item;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    // Onödig?
    public void connectKeysWithContainers() {
        /*String keys = "[Universal Key]|[Kk][Ee][Yy][0-9]+";
        Pattern p = Pattern.compile(keys);
        Matcher matcher = p.matcher(this.object);
        if (matcher.matches()) {
            Container container = new Container();
            Key key = new Key(matcher.group() + "-Container");
            System.out.println(container.show());
        }*/
    }

    public String getObjet() {
        return this.object;
    }

    public boolean isMovable() {
        return movable;
    }

    public String show() {
        return "Item: " + this.object + " can be picked up: " + this.movable;
    }

    public String toString() {
        return this.object;
    }


}