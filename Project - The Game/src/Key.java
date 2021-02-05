public class Key { // extends Container? GameObject?

    String containerName;

    public Key() {
        this.containerName = "No Container";
    }

    public Key(String containerName) {
        this.containerName = containerName;
    }

    public String show() {
        return "Key match: " + this.containerName;
    }
}