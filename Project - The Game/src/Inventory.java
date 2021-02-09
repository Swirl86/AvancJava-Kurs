import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

// TODO all hantering sker med streams
public class Inventory implements Serializable {

    private final int MAX_SIZE;
    private GameObject[] gameObjects;
    private int numberOfItems;

    public Inventory(int maxSize) {
        this.MAX_SIZE = maxSize;
        this.gameObjects = new GameObject[maxSize];
        this.numberOfItems = 0;
    }

    public void addRandomItem() {
        // TODO streama igenom och lägg in filter lägg nytt objekt på första null position
        this.gameObjects[numberOfItems++] = new GameObject();
    }

    public void addTeleport(String name, boolean movable, boolean locked) {
        Container container = new Container(name, movable, locked);
        this.gameObjects[numberOfItems++] = container;

        if (locked) {
            String keyDigit = name.substring(Math.max(name.length() - 2, 0));
            String keyName = "KeyCard" + keyDigit;
            this.gameObjects[numberOfItems++] = new Key(keyName, true, container);
        }
    }

    public void addContainer(String name, boolean movable, boolean locked) {
        Container container = new Container(name, movable, locked);
        this.gameObjects[numberOfItems++] = container;

        if (locked) {
            String keyDigit = name.substring(Math.max(name.length() - 2, 0));
            String keyName = "Key" + keyDigit;
            this.gameObjects[numberOfItems++] = new Key(keyName, true, container);
        }
    }

    public Container getContainer(String containerName) {
        return (Container) Arrays.stream(this.gameObjects)
                .filter(x -> x instanceof Container)
                .filter(x -> x.getObjetName().equals(containerName))
                .findFirst()
                .orElse(null);
    }

    public Key getKey(String keyName) {
        return (Key) Arrays.stream(this.gameObjects)
                .filter(x -> x instanceof Key)
                .filter(x -> x.getObjetName().equals(keyName))
                .findFirst()
                .orElse(null);
    }

    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    public GameObject getGameObject(String object, String pos) {
        int position = Integer.parseInt(pos);
        position = position != 0 ? position - 1 : position;

        return Arrays.stream(this.gameObjects)
                .filter(x -> x.getObjetName().equals(object))
                .skip(position)
                .findFirst()
                .orElse(null);
    }

    public GameObject getFirstGameObject() {
        return this.gameObjects[0];
    }

    public GameObject getContainerGameObject() {
        return this.gameObjects[0];
    }

    public boolean containerObjectIsMovable() {
        return this.gameObjects[0].isMovable();
    }

    public boolean isEmpty() {
        return this.numberOfItems == 0;
    }

    public boolean isMovable(String item, String pos) {
        boolean isMovable = false;
        GameObject object;
        try {
            object = this.getGameObject(item, pos);
            isMovable = this.itemExists(item, pos) && object.isMovable();
        } catch (Exception ignored) {
        }
        return isMovable;
    }

    public boolean gotSpace() {
        return this.numberOfItems != this.MAX_SIZE;
    }

    public boolean itemExists(String object, String pos) {
        int position = Integer.parseInt(pos);
        position = position != 0 ? position - 1 : position;

        return Arrays.stream(this.gameObjects)
                // will filter out all nulls in the stream avoid nullpointerexception
                .filter(Objects::nonNull)
                .skip(position)
                .anyMatch(x -> x.getObjetName().equals(object));
    }

    // Pickup - add to inventory
    public void addItem(GameObject object) {
        // TODO streama igenom och lägg in filter lägg nytt objekt på första null position
        this.gameObjects[numberOfItems++] = object;
    }

    /*public void dropItem(String object, String pos) {
        int position = Integer.parseInt(pos);
        position = position != 0 ? position - 1 : position;

        if (this.numberOfItems != 0 && itemExists(object, pos)) {
            int index = IntStream.range(0, this.numberOfItems)
                    .filter(i -> object.equals(this.gameObjects[i].getObjetName()))
                    .skip(position)
                    .findFirst()
                    .orElse(-1);

            GameObject[] tmp = IntStream.range(0, this.gameObjects.length)
                    .filter(i -> i != index)
                    .mapToObj(i -> this.gameObjects[i])
                    .toArray(GameObject[]::new);
            this.numberOfItems--;

            this.gameObjects = Arrays.copyOf(tmp, this.MAX_SIZE);
        }
    }*/

    public void dropGameObject(GameObject object) {
        if (this.numberOfItems != 0) {
            int index = IntStream.range(0, this.numberOfItems)
                    .filter(i -> object.equals(this.gameObjects[i]))
                    .findFirst()
                    .orElse(-1);

            GameObject[] tmp = IntStream.range(0, this.gameObjects.length)
                    .filter(i -> i != index)
                    .mapToObj(i -> this.gameObjects[i])
                    .toArray(GameObject[]::new);
            this.numberOfItems--;

            this.gameObjects = Arrays.copyOf(tmp, this.MAX_SIZE);
        }
    }


    public String toString() {
        // TODO Gör till stream
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < this.numberOfItems; i++) {
            if (i % 3 == 0) {
                value.append("\n");
            }
            value.append(" | ").append(this.gameObjects[i].getObjetName()).append(" | ");
        }
        return value.toString();
    }

    // For Npc
    public String itemToString() {
        // TODO Gör till stream
        String value = "";
        for (int i = 0; i < this.numberOfItems; i++) {
            if (this.gameObjects[i] != null) {
                value += " | " + this.gameObjects[i].getObjetName() + " | ";
            } else {
                value += "nothing";
            }
        }
        return value;
    }
}
