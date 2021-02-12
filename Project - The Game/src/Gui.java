import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

public class Gui extends JFrame {
    private static final long serialVersionUID = 6696031872468154516L;
    private final String fileName = "TheGame.txt";
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);
    private final Font bigFont = new Font("Times New Roman", Font.PLAIN, 18);
    private final Font boldFont = new Font("Times New Roman", Font.BOLD, 17);
    private final Border lineBorder = BorderFactory.createLineBorder(Color.black);
    private final Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private final String availableCommands =
            "pickup itemName    pickup itemName  2          ---  To pickup the second item with same name. --- \n" +
                    "drop itemName       drop itemName 2               ---  To drop the second item with same name.    --- \n" +
                    "open container12     open container12 key12     --- The key needs to be in your inventory to use. ---\n" +
                    "trade 1    --- Trade first item in your inventory with an npc at a given position in the room. --- \n" +
                    "       [ To win you need to find the Teleporter and KeyCard: use Teleporter KeyCard ]\n" +
                    "               You can SAVE or LOAD your game by entering: save  or  load";

    private Game game;
    private JPanel mainPanel;
    private JPanel navPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    private JTextArea showRoom;
    private JTextArea showPersons;
    private JTextField input;
    private JTextArea inventory;
    private String command;
    private boolean gotCommand;
    private JButton commitButton;
    private JTextArea infoTextArea;
    private JButton leftButton;
    private JButton rightButton;

    public Gui(Game game) {
        this.game = game;
        this.gotCommand = false;
        this.command = "";
        this.setTitle("The Game");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpElements();
        setUpPanel();
        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void updateFrame() {
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        setInfoText("\nSuccess! The game has been loaded from " + this.fileName);
    }

    public String getCommand() {
        if (this.gotCommand) {
            this.gotCommand = false;
            setInfoText(this.availableCommands);
            return this.command;
        }
        return "-1";
    }

    public String getAvailableCommands() {
        return this.availableCommands;
    }

    public void setShowRoom(String roomDescription) {
        this.showRoom.setText("Room: " + roomDescription);
    }

    public void setShowPersons(Person[] persons) {
        String roomNpcInfo = "";
        for (Person person : persons) {
            if (person != null) {
                roomNpcInfo += person.toString() + "\n";
            }
        }

        this.showPersons.setText(roomNpcInfo);
    }

    public void setShowInventory(Inventory i) {
        this.inventory.setText("Inventory: " + i.toString());
    }

    public void setInfoText(String text) {
        this.infoTextArea.setText(text);
    }

    private void setUpPanel() {

        this.navPanel.add(showPersons);

        this.topPanel.add(this.infoTextArea);

        JScrollPane scroll = new JScrollPane(this.showRoom,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.middlePanel.add(scroll);
        scroll = new JScrollPane(this.inventory,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.middlePanel.add(scroll);

        this.bottomPanel.add(this.input);
        this.bottomPanel.add(this.commitButton);
        this.bottomPanel.add(this.leftButton);
        this.bottomPanel.add(this.rightButton);

        this.mainPanel.add(this.navPanel);
        this.mainPanel.add(this.topPanel);
        this.mainPanel.add(this.middlePanel);
        this.mainPanel.add(this.bottomPanel);
    }

    private void setUpElements() {
        this.mainPanel = new JPanel(null);
        this.mainPanel.setBackground(new Color(62, 181, 157));
        this.navPanel = new JPanel(new GridLayout(1, 1));
        this.navPanel.setBounds(15, 20, 750, 110);

        this.topPanel = new JPanel(new GridLayout(1, 1));
        this.topPanel.setBounds(15, 150, 750, 160);

        this.middlePanel = new JPanel(new GridLayout(1, 2));
        this.middlePanel.setBounds(15, 330, 750, 230);

        this.bottomPanel = new JPanel(new GridLayout(2, 4));
        this.bottomPanel.setBounds(15, 580, 750, 150);

        this.infoTextArea = new JTextArea(this.availableCommands);
        this.infoTextArea.setForeground(new Color(76, 0, 153));
        this.infoTextArea.setFont(this.bigFont);
        this.infoTextArea.setLineWrap(true);
        this.infoTextArea.setBorder(getBorder("Information"));

        this.showRoom = new JTextArea();
        this.showRoom.setFont(this.normalFont);
        this.showRoom.setLineWrap(true);
        this.showRoom.setBorder(getBorder("Room Information"));

        this.showPersons = new JTextArea("Persons");
        this.showPersons.setFont(this.normalFont);
        this.showPersons.setLineWrap(true);
        this.showPersons.setBorder(getBorder("Npc Information"));

        this.inventory = new JTextArea("Inventory");
        this.inventory.setFont(this.normalFont);
        this.inventory.setLineWrap(true);
        this.inventory.setBorder(getBorder("My Inventory"));

        this.input = new JTextField("pickup ");
        this.input.setFont(this.bigFont);
        this.input.setBorder(getBorder("Write command"));

        this.infoTextArea.setEditable(false);
        this.showPersons.setEditable(false);
        this.showRoom.setEditable(false);
        this.inventory.setEditable(false);

        ActionListener inputListener = e -> {
            if (e.getSource() == this.commitButton) {
                this.command = input.getText();
            } else {
                this.command = e.getActionCommand();
            }
            this.gotCommand = true;
        };

        input.addActionListener(inputListener);
        this.commitButton = new JButton("Commit");
        this.commitButton.setFont(this.bigFont);
        this.commitButton.addActionListener(inputListener);

        this.leftButton = new JButton("Left");
        this.leftButton.setFont(this.bigFont);
        this.leftButton.addActionListener(inputListener);

        this.rightButton = new JButton("Right");
        this.rightButton.setFont(this.bigFont);
        this.rightButton.addActionListener(inputListener);

    }

    public CompoundBorder getBorder(String title) {
        return BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(this.lineBorder, title),
                this.emptyBorder);
    }

    public Game load() {
        Game game = null;
        try {
            FileInputStream fis = new FileInputStream(this.fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception ignored) {
        }
        return game;
    }

    public boolean save() {
        boolean saved = false;
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            FileOutputStream fout = new FileOutputStream(this.fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this.game);
            oos.close();
            saved = true;
        } catch (Exception ignored) {
        }
        lock.unlock();
        return saved;
    }

}