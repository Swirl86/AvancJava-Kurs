import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class Inventory {

    private GameObject[] gameObjects;
    private int MAX;
    private int numberOfItems;

    public Inventory(int maxSize) {
        this.MAX = maxSize;
        this.gameObjects = new GameObject[maxSize];
        this.numberOfItems = 0;
    }

    public int getNumberOfItems(){
        return this.numberOfItems;
    }

    public boolean gotSpace() {
        return this.numberOfItems != this.MAX;
    }

    public void addRandomItem(){
        gameObjects[numberOfItems++] = new GameObject();
    }


    public void addItem(String item){
        gameObjects[numberOfItems++] = new GameObject(item);
    }
    //Nödvändig?
    public boolean pickUpItem(GameObject object){
        // TODO Gör till stream
        boolean addedItem = false;
        if(gotSpace()){
            gameObjects[numberOfItems++] = object;
            addedItem = true;
        } else {
            System.out.println("Inventory is FULL");
        }
        return addedItem;
    }
//Nödvändig?
    public void tradeItem(String drop, GameObject pickUp) {
        dropItem(drop);
        pickUpItem(pickUp);
    }

    public void dropItem(String object){
        if (this.numberOfItems != 0 && itemExists(object)) {
            int index = IntStream.range(0, this.numberOfItems)
                    .filter(i -> object.equals(this.gameObjects[i].getObjet()))
                    .findFirst()
                    .orElse(-1);
            this.gameObjects = IntStream.range(0, this.gameObjects.length)
                    .filter(i -> i != index)
                    .mapToObj(i -> this.gameObjects[i])
                    .toArray(GameObject[]::new);
            this.numberOfItems--;
        }
    }
    // TODO får felmedelanden om det inte finns
    public boolean itemExists(String object){
        return Arrays.stream(this.gameObjects)
                // will filter out all nulls in the stream
                .filter(Objects::nonNull)
                .anyMatch(x -> x.getObjet().equals(object));
    }

    public GameObject getItemFromInventory(String object) {
       // Stream<GameObject> myStream = Arrays.stream(this.gameObjects);
        return Arrays.stream(this.gameObjects)
                .filter(x -> x.getObjet().equals(object))
                .findFirst().get();
    }


    public void show(){
        for (int i = 0; i < this.numberOfItems; i++) {
           this.gameObjects[i].show();
        }
    }

    public String toString(){
        // TODO Gör till stream
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < this.numberOfItems; i++) {
            if(i % 2 == 0){
                value.append("\n");
            }
            value.append(" | ").append(this.gameObjects[i].getObjet()).append(" | ");
        }
        return value.toString();
    }
    public String itemToString(){
        String value = "";
        for (int i = 0; i < this.numberOfItems; i++) {
            value += " | " + this.gameObjects[i].getObjet() + " | ";
        }
        return value;
    }
}
