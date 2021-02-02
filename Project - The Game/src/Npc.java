public abstract class Npc {

    protected String name;

    protected Npc(String name) {
        this.name = name;
    }

    public String getNpcName() {
        return this.name;
    }

    protected abstract void showPerson();

}