import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Room implements Serializable {
    private final int NPC_MAX_SIZE = 3;
    private final int index;
    private String roomName;
    private String description;
    private final int inventorySize;
    private Inventory inventory;
    // Keep an eye on npc's in the room
    private Person[] persons;
    private int nrOfPersonsInRoom;

    public Room(int index, String name, String description) {
        this.index = index;
        this.roomName = name;
        this.description = description;
        this.inventorySize = getRandomSize() + 11;
        this.inventory = new Inventory(this.inventorySize);
        this.persons = new Person[this.NPC_MAX_SIZE];

        fillRoomInventory();
    }

    public int getRandomSize() {
        return (int) (Math.random() * 10) + 5;
    }

    public void fillRoomInventory() {
        Random rd = new Random();
        int max = this.inventorySize - 11;

        int x = rd.nextInt((max) + 1);
        // Fill With some items and leave some empty spaces
        for (int i = 0; i < max; i++) {
            if (i != x) {
                this.inventory.addRandomItem();
            } else {
                generateContainers(); // TODO catch the matching key and add it to random room
            }
        }
    }

    public void addTeleport() {
        int digit = (int) (Math.random() * 90) + 10;
        String name = "Teleporter" + digit;
        this.inventory.addTeleport(name, true, true);
    }

    private void generateContainers() {
        int digit = (int) (Math.random() * 90) + 10;
        String name = "Container" + digit;
        Random rd = new Random();
        boolean locked = rd.nextBoolean();
        boolean movable = rd.nextBoolean();

        this.inventory.addContainer(name, movable, locked);
    }

    public void addPerson(Person person) {
        if (this.nrOfPersonsInRoom != this.NPC_MAX_SIZE) {
            this.persons[this.nrOfPersonsInRoom++] = person;
        }
    }

    public void removePerson(Person person) {
        if (this.nrOfPersonsInRoom != 0) {
            int index = IntStream.range(0, this.nrOfPersonsInRoom)
                    .filter(i -> person.equals(this.persons[i]))
                    .findFirst()
                    .orElse(-1);

            Person[] tmp = IntStream.range(0, this.persons.length)
                    .filter(i -> i != index)
                    .mapToObj(i -> this.persons[i])
                    .toArray(Person[]::new);
            this.nrOfPersonsInRoom--;

            this.persons = Arrays.copyOf(tmp, this.NPC_MAX_SIZE);
        }
    }

    public Person[] getPersonsFromRoom() {
        if (this.nrOfPersonsInRoom == 0) {
            // Create a ghost so the room don't look so empty.
            this.persons[nrOfPersonsInRoom++] = new Person("Ghost-Bob");
            this.persons[0].setPhrase("I am a ghost. . . I am not here -_- ");
            GameObject obj = this.persons[0].getInventory().getFirstGameObject();
            this.persons[0].getInventory().dropGameObject(obj);
            obj.setObjetName("Ghost-Candy"); // Better Catch Phrase for a ghost
            this.persons[0].getInventory().addGameObject(obj);
        }
        return this.persons;
    }

    public int getIndex() {
        return this.index;
    }

    public synchronized Inventory getInventory() {
        return this.inventory;
    }

    /* Methods to shorten rows when fetching inventory values from Game */
    public Container getContainer(String container) {
        return this.inventory.getContainer(container);
    }

    public synchronized boolean dropGameObject(GameObject object) {
        return this.inventory.dropGameObject(object);
    }

    public synchronized boolean addGameObject(GameObject object) {
        return this.inventory.addGameObject(object);
    }

    public synchronized boolean gotSpace() {
        return this.inventory.gotSpace();
    }

    public synchronized boolean isMovable(String object, String position) {
        return this.inventory.isMovable(object, position);
    }

    public synchronized boolean itemExists(String object, String position) {
        return this.inventory.itemExists(object, position);
    }

    public synchronized GameObject getGameObject(String object, String position) {
        return this.inventory.getGameObject(object, position);
    }

    public String toString() {
        return this.index + " - " + this.roomName + "\n~~ " +
                this.description + " ~~" + "\n" + this.inventory.toString();
    }

}
