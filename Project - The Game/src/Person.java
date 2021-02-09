import java.io.Serializable;

public class Person extends Npc implements Serializable {
    /*
    – Gå till ett anliggande rum.
    – Plocka upp en objekt från det rum hen befinner sig i.
    – lägga ned ett objekt som hen bär på i rummet.
    – säga sin unika fras.
    */

    private int position;

    public Person(String name, int startingRoom) {
        super(name);
        this.position = startingRoom;
    }

    public Person(String name) {
        super(name);
        this.position = 0;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}