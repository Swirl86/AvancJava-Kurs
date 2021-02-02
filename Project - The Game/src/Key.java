public class Key extends GameObject {

    String containerName;

    public Key() {
        this.containerName =  "No Container";
    }

    public Key(String containerName) {
        this.containerName =  containerName;
    }

    public void show(){
        System.out.println("Key match: " + this.containerName);
    }
}