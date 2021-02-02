import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Gui extends JFrame {
    private final String[] availableCommans = {"left", "right", "pickup", "drop", "trade", "follow", "1", "2", "3", "4"};

    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);

    private JPanel mainPanel;
    private JPanel navPanel;
    private JPanel topPanel;
    private JPanel middelPanel;
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
    private JButton pickupButton;
    private JButton dropButton;

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

    //Returnera det senaste commitade kommandot
    public String getCommand(){
        if (this.gotCommand){
            this.gotCommand = false;
            return this.command;
        }
        //return null;
        return "-1";
    }

    public void setCommand(String command){
            this.command = command;
            this.gotCommand = true;
    }

    public boolean checkCommand(){
        return Arrays.asList(availableCommans).contains( this.command );
    }

    //Här kan man updatera respektive fält:
    public void setShowRoom(String roomDescription){
        this.showRoom.setText("Room: " + roomDescription );
    }
    public void setShowPersons(Person person){
        this.showPersons.setText(person.toString());
    }
    public void setShowInventory(Inventory i){
        this.inventory.setText("Inventory: " + i.toString());
    }

    //Add person to room
    public void setPerson(Person person){
        this.showPersons.setText(person.toString());
    }


    public void gotCommand(){
        this.gotCommand = false;
    }

    private void setUpPanel(){

        this.navPanel.add(showPersons);

        this.topPanel.add(this.infoTextArea);

        this.middelPanel.add(this.showRoom);
        this.middelPanel.add(this.inventory);

        this.bottomPanel.add(this.input);
        this.bottomPanel.add(this.commitButton);
        this.bottomPanel.add( this.leftButton);
        this.bottomPanel.add( this.rightButton);

        this.mainPanel.add(this.navPanel);
        this.mainPanel.add(this.topPanel);
        this.mainPanel.add(this.middelPanel);
        this.mainPanel.add(this.bottomPanel);
    }
    private void setUpElements(){
        this.mainPanel = new JPanel(null);
        this.navPanel = new JPanel(new GridLayout(1,1));
        this.navPanel.setBounds(50, 15, 700, 50);;
        this.topPanel = new JPanel(new GridLayout(1,1));
        this.topPanel.setBounds(50, 100, 700, 100);
        this.topPanel.setBackground(Color.gray);
        this.middelPanel = new JPanel(new GridLayout(1,2));
        this.middelPanel.setBounds(50, 220, 700, 250);
        this.bottomPanel = new JPanel(new GridLayout(2,4));
        this.bottomPanel.setBounds(50, 490, 700, 150);

        this.infoTextArea = new JTextArea("MAIN TEXT GAME AREA\nCommands: pickup itemName   drop itemName\nComments will be here");
        this.infoTextArea.setBackground(Color.lightGray);
        this.infoTextArea.setFont(normalFont);
        this.infoTextArea.setLineWrap(true);
        this.showRoom = new JTextArea("Room: ");
        this.showRoom.setFont(normalFont);
        this.showRoom.setLineWrap(true);
        this.showPersons = new JTextArea("Persons");
        this.showPersons.setFont(normalFont);
        this.showPersons.setLineWrap(true);
        this.inventory = new JTextArea("Inventory");
        this.inventory.setFont(normalFont);
        this.inventory.setLineWrap(true);
        this.input = new JTextField("Give command");
        this.showPersons.setEditable(false);
        this.showRoom.setEditable(false);
        this.inventory.setEditable(false);

        ActionListener inputListener = e -> {
            if (e.getSource() == this.commitButton){
                this.command = input.getText();
            } else {
                this.command =  e.getActionCommand();
            }
            this.gotCommand = true;
            System.out.println(this.command);
        };

        input.addActionListener(inputListener);
        this.commitButton = new JButton("Commit");
        this.commitButton.addActionListener(inputListener);

        this.leftButton = new JButton("Left");
        this.leftButton.addActionListener(inputListener);

        this.rightButton = new JButton("Right");
        this.rightButton.addActionListener(inputListener);

    }

}