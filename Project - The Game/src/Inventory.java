import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class Inventory {

    private GameObject[] gameObjects;
    private final int MAX_SIZE;
    private int numberOfItems;

    public Inventory(int maxSize) {
        this.MAX_SIZE = maxSize;
        this.gameObjects = new GameObject[maxSize];
        this.numberOfItems = 0;
    }

    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    public boolean gotSpace() {
        return this.numberOfItems != this.MAX_SIZE;
    }

    public boolean isMovable(String item, String pos) {
        boolean isMovable = false;
        GameObject object;
        try {
            object = this.getItemFromInventory(item, pos);
            isMovable = this.itemExists(item, pos) && object.isMovable();
        } catch (Exception ignored) {
        }
        return isMovable;
    }

    public void createContainer() {
        // Todo Container & Key
        // Container & Key : Lägg till check ifall det är kista som randomiseras
        // om den är låst hämta nyckel
        // randomisera två siffrigt nummer dör container till Container12
        // Skapa matchande nyckel med namn Key12
        //  gameObjects[numberOfItems++] = new Container();
        // Lägg till mer checkar och kommandos för open chest


        // Overida metoder för att komma åt från Container
        Container container = new Container("Container21");
        int index = numberOfItems++;
        this.gameObjects[index] = container;
        //  System.out.println(this.gameObjects[index].show());
        // Är den låst? Generera nyckel
    }

    public void addRandomItem() {
        this.gameObjects[numberOfItems++] = new GameObject();
    }

    // Pickup - add to inventory
    public void addItem(String item) {
        this.gameObjects[numberOfItems++] = new GameObject(item);
    }

    public void dropItem(String object, String pos) {
        int position = Integer.parseInt(pos);
        position = position != 0 ? position - 1 : position;

        if (this.numberOfItems != 0 && itemExists(object, pos)) {
            int index = IntStream.range(0, this.numberOfItems)
                    .filter(i -> object.equals(this.gameObjects[i].getObjet()))
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
    }

    public boolean itemExists(String object, String pos) {
        int position = Integer.parseInt(pos);
        position = position != 0 ? position - 1 : position;

        return Arrays.stream(this.gameObjects)
                // will filter out all nulls in the stream avoid nullpointerexception
                .filter(Objects::nonNull)
                .skip(position)
                .anyMatch(x -> x.getObjet().equals(object));
    }

    // Todo lägg till mer checkar ifall det blir null hantera
    public GameObject getItemFromInventory(String object, String pos) {
        int position = Integer.parseInt(pos);
        position = position != 0 ? position - 1 : position;

        return Arrays.stream(this.gameObjects)
                .filter(x -> x.getObjet().equals(object))
                .skip(position)
                .findFirst()
                .orElse(null);
    }

    public void show() {
        for (int i = 0; i < this.numberOfItems; i++) {
            this.gameObjects[i].show();
        }
    }

    public String toString() {
        // TODO Gör till stream
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < this.numberOfItems; i++) {
            if (i % 2 == 0) {
                value.append("\n");
            }
            value.append(" | ").append(this.gameObjects[i].getObjet()).append(" | ");
        }
        return value.toString();
    }

    // For Npc
    public String itemToString() {
        // TODO Gör till stream
        String value = "";
        for (int i = 0; i < this.numberOfItems; i++) {
            value += " | " + this.gameObjects[i].getObjet() + " | ";
        }
        return value;
    }
}
