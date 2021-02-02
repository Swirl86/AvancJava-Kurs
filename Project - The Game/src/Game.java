public class Game {

    private Gui gui;
    private Update update;
    private Player player;
    private Person[] npcs;
    private Room[] rooms;
    boolean GameIsOn = true;

    public Game() {
        this.gui = new Gui();
        this.player = new Player(5);
        this.npcs = new Person[3];
        this.rooms = new Room[4];
        addNpcs();
        addRooms();

        this.update = new Update(this.gui, this.player, this.npcs, this.rooms, this.GameIsOn);
        Thread thread = new Thread(update);
        thread.start();
        startGame();
    }

    public void addNpcs() {
        this.npcs[0] = new Person("Jason");
        this.npcs[1] = new Person("Freddy");
        this.npcs[2] = new Person("Ture Sventon");
    }

    public void addRooms() {
        for (int i = 0; i < 4; i++) {
            this.rooms[i] = new Room(String.valueOf(i + 1));
        }
        int index = (int) (Math.random() * 3);
        this.player.setRoom(this.rooms[index]);
        this.gui.setShowRoom(this.player.getRoomName() + " " + this.rooms[index].getInventory());
        this.gui.setShowInventory(this.player.getInventory());

        // System.out.println(player.getRoom().getName());
      /*  for (Room room: rooms) {
            System.out.println(room.getName());
        } */

    }

    public void startGame() {
        //ReentrantLock lock = new ReentrantLock();
        long startTime = 0;
        String choice = "-1";
        int index = -1;

        while (GameIsOn) {
            startTime = System.currentTimeMillis();
            choice = gui.getCommand();
            String[] command = choice.split("\\s", 2);
            //lock.lock();
            switch (command[0]) {
                case "Left":
                    try {
                        System.out.println("LEFT-----> " + command[0]);
                        // System.out.println(command[1]);
                        int newRoom = index - 1;
                        index = Integer.valueOf(this.player.getRoomName());

                        if (checkAdjoiningRoom(index, newRoom)) {
                            this.player.setRoom(this.rooms[newRoom - 1]);
                            this.gui.setShowRoom(this.player.getRoomName() + ": " + this.rooms[newRoom - 1].getInventory());
                            this.gui.setShowInventory(this.player.getInventory());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Right":
                    System.out.println("RIGHT-----> " + command[0]);
                    //  System.out.println(command[1]);
                    int newRoom = index + 1;
                    index = Integer.valueOf(this.player.getRoomName());

                    if (checkAdjoiningRoom(index, newRoom)) {
                        this.player.setRoom(this.rooms[newRoom - 1]);
                        this.gui.setShowRoom(this.player.getRoomName() + ": " + this.rooms[newRoom - 1].getInventory());
                        this.gui.setShowInventory(this.player.getInventory());
                    }
                    break;
                case "pickup":
                    //TODO fixa kollarna, lägg till bara lowercase kolla varför den fortsätter ta in värden
                    // System.out.println(command[1]);
                    index = Integer.valueOf(this.player.getRoomName());
                    // TODO hantera om item inte finns eller om inventory är fullt skriv ut medelande?
                    if (this.rooms[index - 1].getInventory().itemExists(command[1]) &&
                            this.player.getInventory().gotSpace()) {
                        // GameObject item = this.rooms[roomNr - 1].getItem(command[1]);
                        this.player.addToInventory(command[1]);
                        this.rooms[index - 1].removeItem(command[1]);
                        this.gui.setShowInventory(this.player.getInventory());
                        this.gui.setShowRoom(this.player.getRoomName() + ": " + this.rooms[index - 1].getInventory());
                    }
                    break;
                case "drop":
                    index = Integer.valueOf(this.player.getRoomName());
                    // TODO hantera om item inte finns eller om inventory är fullt skriv ut medelande?
                    if (this.rooms[index - 1].getInventory().gotSpace() &&
                            this.player.getInventory().itemExists(command[1])) {

                        this.player.removeFromInventory(command[1]);
                        this.rooms[index - 1].addItem(command[1]);
                        this.gui.setShowInventory(this.player.getInventory());
                        this.gui.setShowRoom(this.player.getRoomName() + ": " + this.rooms[index - 1].getInventory());
                    }
                    break;
                case "trade":
                    index = Integer.valueOf(this.player.getRoomName());
                    // TODO fånga upp npc som är i rummet och kolla inventory pausa npc så de inte går
                    // Eller är trade att man släpper sak i inventory och pockar upp ett annat direkt
                    if (this.player.getInventory().itemExists(command[1])) {
                        this.player.removeFromInventory(command[1]);
                        this.rooms[index - 1].addItem(command[1]);
                        this.gui.setShowInventory(this.player.getInventory());
                        this.gui.setShowRoom(this.player.getRoomName() + ": " + this.rooms[index - 1].getInventory());
                    }
                    break;
                default:
                    // TODO gör eget case för höger vänster steg med pilar och bättre default case
                    //System.out.println("DEFAULT CASE");
                    break;
            }
            //lock.unlock();
// Todo checka om dörren är upplåstdvs spelet klart
            //waitForNextInterval(startTime);
        }
    }

    private boolean checkAdjoiningRoom(int currentRoom, int newRoom) {
        boolean validRoomChange = false;
        if (currentRoom == 1 && newRoom == 2) {
            validRoomChange = true;
        } else if (currentRoom == 2 && newRoom == 1 || currentRoom == 2 && newRoom == 3) {
            validRoomChange = true;
        } else if (currentRoom == 3 && newRoom == 2 || currentRoom == 3 && newRoom == 4) {
            validRoomChange = true;
        } else if (currentRoom == 4 && newRoom == 3) {
            validRoomChange = true;
        }
        //  System.out.println(validRoomChange);
        return validRoomChange;
    }

    private void waitForNextInterval(long startTime) {
        long currentTime = System.currentTimeMillis();
        long endTime = startTime + 1000;
        long delta = endTime - currentTime;
        if (delta > 0) {
            try {
                Thread.sleep(delta);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
