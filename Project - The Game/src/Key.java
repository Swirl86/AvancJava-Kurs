import java.io.Serializable;

public class Key extends GameObject implements Serializable {

    Container container;

    public Key(String name, boolean movable, Container container) {
        super(name, movable);
        this.container = container;
    }

    public boolean fit(Container c) {
        return this.container.getObjetName().equals(c.getObjetName());
    }

}