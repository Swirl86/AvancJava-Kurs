import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Gui extends JFrame {
    private final String[] availableCommands = {"left", "right", "pickup", "drop", "trade", "follow"};
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);
    private final Font bigFont = new Font("Times New Roman", Font.PLAIN, 18);
    private final Border lineBorder = BorderFactory.createLineBorder(Color.black);
    private final Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

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

    public Gui(){
        this.gotCommand = false;
        this.command = "";
        this.setTitle("Game");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpElements();
        setUpPanel();
        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    public String getCommand() {
        if (this.gotCommand) {
            this.gotCommand = false;
            setInfoText("Available Commands: pickup itemName     drop itemName\n");
            return this.command;
        }
        //return null;
        return "-1";
    }

    // TODO updatera så den stämmer
    public boolean checkCommand() {
        return Arrays.asList(availableCommands).contains(this.command);
    }


    public void setShowRoom(String roomDescription) {
        this.showRoom.setText("Room: " + roomDescription);
    }

    public void setShowPersons(Person person) {
        this.showPersons.setText(person.showPerson());
    }

    public void setShowInventory(Inventory i) {
        this.inventory.setText("Inventory: " + i.toString());
    }

    //Add person to room
    public void setPerson(Person person) {
        this.showPersons.setText(person.showPerson());
    }

    public void setInfoText(String text) {
        this.infoTextArea.setText(text);
    }

    public void gotCommand() {
        this.gotCommand = false;
    }

    private void setUpPanel() {

        this.navPanel.add(showPersons);

        this.topPanel.add(this.infoTextArea);

        this.middlePanel.add(this.showRoom);
        this.middlePanel.add(this.inventory);

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
        this.mainPanel.setBackground(Color.lightGray);
        this.navPanel = new JPanel(new GridLayout(1, 1));
        this.navPanel.setBounds(50, 15, 700, 70);

        this.topPanel = new JPanel(new GridLayout(1, 1));
        this.topPanel.setBounds(50, 100, 700, 130);

        this.middlePanel = new JPanel(new GridLayout(1, 2));
        this.middlePanel.setBounds(50, 250, 700, 220);

        this.bottomPanel = new JPanel(new GridLayout(2, 4));
        this.bottomPanel.setBounds(50, 490, 700, 150);

        this.infoTextArea = new JTextArea("Available Commands:\npickup itemName      drop itemName\n" +
                "pickup itemName 2   drop itemName 2\n");
        this.infoTextArea.setForeground(Color.BLUE);
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
            System.out.println(this.command);
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

}