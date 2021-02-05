public class Person extends Npc {
    /*
    – Gå till ett anliggande rum.
    – Plocka upp en objekt från det rum hen befinner sig i.
    – lägga ned ett objekt som hen bär på i rummet.
    – säga sin unika fras.
    */

    private final String[] actions = {"Pickup", "Drop", "Move", "Trade"};
    private Inventory inventory;
    private String action;

    public Person(String name) {
        super(name);
        this.inventory = new Inventory(1);
        this.action = "Start moving";
        fillInventory();
    }


    private void fillInventory() {
        this.inventory.addRandomItem();
    }

    public int getNumberOfItems() {
        return this.inventory.getNumberOfItems();
    }

    public int getRandomNumber() {
        return (int) (Math.random() * 4) + 1;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    // prepp
    public void pickUpItem() {
    }

    public void dropItem() {
    }

    public void tradeItem() {
    }

    public void changeRoom() {
    }

    @Override
    public String showPerson() {
        return this.name + " is carrying " + this.inventory.itemToString() + " "
                + getRandomPhrase();
    }

    @Override
    protected String getRandomPhrase() {
        return this.getPhrase();
    }

    @Override
    protected String getNpcName() {
        return this.name;
    }

    public String toString() {
        return this.name + " is carrying " + this.inventory.itemToString() + " ~ ~ "
                + getRandomPhrase() + " ~ ~ ";
    }
}