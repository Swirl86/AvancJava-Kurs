public class Person extends Npc {
    /*
    – Gå till ett anliggande rum.
    – Plocka upp en objekt från det rum hen befinner sig i.
    – lägga ned ett objekt som hen bär på i rummet.
    – säga sin unika fras.
    */

    private final String[] actions = {"Pick up", "Drop", "Move", "Trade"};
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
    public int getNumberOfItems(){
        return this.inventory.getNumberOfItems();
    }
    public int getRandomNumber() {
        return (int) (Math.random() * 4) + 1;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    // prepp
    public void pickUpItem() { }
    public void dropItem() { }
    public void tradeItem() { }
    public void changeRoom() { }

    @Override
    public void showPerson() {
        System.out.print( this.name + "'s inventory: " + this.inventory.toString());
        //System.out.println( this.inventory.getItemsFromInventory());
    }
    public String toString() {
       return this.name + " carry's " + this.inventory.itemToString();
    }
}