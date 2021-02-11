import java.io.Serializable;

public class Person extends Npc implements Serializable {
 
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