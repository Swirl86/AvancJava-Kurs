import java.io.Serializable;
import java.util.Random;

public abstract class Npc implements Serializable {
    private final String[] phrases = {"“Every story needs its hero. . And its villain.”",
            "“Every villain is a hero in his own mind.”", "“I’d buy that for a gold…”",
            "“By my Great Aunt Myrra’s beard!”", "“Bababooy, Bababooy, his head went kablooy.”",
            "“That’s the way the meatball bounces!”", "“Keep your gold close and your dagger closer.”"};

    protected String name;
    protected String catchPhrase;
    private Inventory inventory;

    protected Npc(String name) {
        this.name = name;
        this.catchPhrase = getRandomPhrase();
        this.inventory = new Inventory(1);
        this.inventory.addRandomItem();
    }

    public int getNumberOfItems() {
        return this.inventory.getNumberOfItems();
    }

    public String getRandomPhrase() {
        int i = new Random().nextInt(this.phrases.length);
        return this.phrases[i];
    }

    public void setPhrase(String phrase) {
        this.catchPhrase = phrase;
    }

    public String getName() {
        return this.name;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void dropGameObject(GameObject object) {
        this.inventory.dropGameObject(object);
    }

    public String toString() {
        String inventory = this.inventory.isEmpty() ? "nothing " : this.inventory.itemToString();
        return this.name + " is carrying " + inventory + " "
                + getRandomPhrase();
    }
}