public class Update implements  Runnable {
    private Gui gui;
    private Player player;
    private Person[] npcs;
    private Room[] rooms;
    private int currentRoom;
    boolean GameIsOn;

    public Update(Gui gui, Player player, Person[] npcs, Room[] rooms, boolean gameIsOn) {
        this.gui = gui;
        this.player = player;
        this.npcs = npcs;
        this.rooms = rooms;
        currentRoom = Integer.valueOf(this.player.getRoomName());
        this.GameIsOn = gameIsOn;
    }

    @Override
    public void run() {
        // Test
        while(GameIsOn){
            for (int i = 0; i < 3; i++) {
               changeNpc(this.npcs[i]);
               try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    private void changeNpc(Person npc) {
        this.gui.setPerson(npc);
       // System.out.println(npc);
    }
}
