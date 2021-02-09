import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Room implements Serializable {
    private final int MAX_SIZE = 3;
    private final int index;
    private String roomName;
    private String description;
    private final int inventorySize;
    private Inventory inventory;
    private boolean isLocked;
    // Keep eye on npc in the room
    private Person[] persons;
    private int nrOfPersonsInRoom;

    public Room(int index, String name, String description) {
        this.index = index;
        this.roomName = name;
        this.description = description;
        this.inventorySize = getRandomSize() + 11;
        this.inventory = new Inventory(this.inventorySize);
        this.isLocked = false;
        fillRoomInventory();

        this.persons = new Person[this.MAX_SIZE];
    }

    // To many items in a room, 5-15?
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
                generateContainers();
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
        if (this.nrOfPersonsInRoom != this.MAX_SIZE) {
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

            this.persons = Arrays.copyOf(tmp, this.MAX_SIZE);
        }
    }

    public int getNrOfNpcInRoom() {
        return this.nrOfPersonsInRoom;
    }

    public Person[] getPersonsFromRoom() {
        if (this.nrOfPersonsInRoom == 0) {
            this.persons[nrOfPersonsInRoom++] = new Person("Ghost-Bob");
            this.persons[0].setPhrase("I am a ghost. . . I am not here -_- ");
        }

        return this.persons;
    }

    public int getIndex() {
        return this.index;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.index + " - " + this.roomName + "\n~~ " +
                this.description + " ~~" + "\n" + this.getInventory();
    }

    /* Shorter rows when fetching inventory values */
    public Container getContainer(String container) {
        return this.inventory.getContainer(container);
    }

    public void dropGameObject(GameObject object) {
        this.inventory.dropGameObject(object);
    }

    public void addItem(GameObject object) {
        this.inventory.addItem(object);
    }

    public boolean gotSpace() {
        return this.inventory.gotSpace();
    }
}
