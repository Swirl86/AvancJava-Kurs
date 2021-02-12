import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

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
        this.gameObjects[numberOfItems++] = new GameObject();
    }

    // End game item
    public void addTeleport(String name, boolean movable, boolean locked) {
        Container container = new Container(name, movable, locked);
        this.gameObjects[numberOfItems++] = container;
        connectKey(name, locked, container, "KeyCard");
    }

    public void addContainer(String name, boolean movable, boolean locked) {
        Container container = new Container(name, movable, locked);
        this.gameObjects[numberOfItems++] = container;
        connectKey(name, locked, container, "Key");
    }

    public void connectKey(String name, boolean locked, Container container, String key) {
        if (locked) {
            String keyDigit = name.substring(Math.max(name.length() - 2, 0));
            String keyName = key + keyDigit;
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

    public synchronized GameObject getGameObject(String object, String pos) {
        int position = Integer.parseInt(pos);
        position = position != 0 ? position - 1 : position;

        return Arrays.stream(this.gameObjects)
                .filter(x -> x.getObjetName().equals(object))
                .skip(position)
                .findFirst()
                .orElse(null);
    }

    public synchronized GameObject getRandomGameObject() {
        int skipIndex = new Random().nextInt(this.numberOfItems - 1);
        return Arrays.stream(this.gameObjects)
                .skip(skipIndex)
                .findAny()
                .get();
    }

    // Only one space in container and Npc
    public synchronized GameObject getFirstGameObject() {
        return Arrays.stream(this.gameObjects)
                .findFirst()
                .orElse(null);
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
        try { // avoid nullpointerexception
            synchronized (this) {
                object = this.getGameObject(item, pos);
                isMovable = this.itemExists(item, pos) && object.isMovable();
            }
        } catch (Exception ignored) {
        }
        return isMovable;
    }

    public boolean gotSpace() {
        return this.numberOfItems != this.MAX_SIZE;
    }

    public boolean itemExists(String object, String pos) {
        int prevPos = Integer.parseInt(pos);
        prevPos = prevPos != 0 ? prevPos - 1 : prevPos;

        return Arrays.stream(this.gameObjects)
                // filter out all nulls in the stream avoid nullpointerexception
                .filter(Objects::nonNull)
                .skip(prevPos)
                .anyMatch(x -> x.getObjetName().equals(object));
    }

    // Pickup
    public boolean addGameObject(GameObject object) {
        boolean success = false;
        synchronized (this) {
            try {
                this.gameObjects[numberOfItems++] = object;
                success = true;
            } catch (Exception ignored) {
            }
        }
        return success;
    }

    public boolean dropGameObject(GameObject object) {
        boolean success = false;
        synchronized (this) {
            try {
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
                    success = true;
                }
            } catch (Exception ignored) {
            }
        }
        return success;
    }

    public String toString() {
        StringBuilder value = new StringBuilder();
        synchronized (this) {
            for (int i = 0; i < this.numberOfItems; i++) {
                if (i % 3 == 0) {
                    value.append("\n");
                }
                value.append(" | ").append(this.gameObjects[i].getObjetName()).append(" | ");
            }
        }
        return value.toString();
    }

    // toString for one item, Npc and container
    public String itemToString() {
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
