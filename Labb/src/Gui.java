import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    private JPanel panel;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton button;
    private JTextArea outputArea;
    private String nameCommand;
    private String phoneCommand;
    private String emailCommand;
    private boolean gotCommand;

    public Gui(){
        this.gotCommand = false;
        this.setTitle("Game");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.nameCommand = "";
        this.phoneCommand = "";
        this.emailCommand = "";
        setUpElements();
        setUpPanel();
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);
    }

    public String getNameCommand(){
        if (this.gotCommand){
            return this.nameCommand;
        }
        return null;

    }
    public String getPhoneCommand(){
        if (this.gotCommand){
            return this.phoneCommand;
        }
        return null;

    }
    public String getEmailCommand(){
        if (this.gotCommand){
            return this.emailCommand;
        }
        return null;
    }

    public void setOutputArea(String str) {
        this.outputArea.setText(str);
    }


    private void setUpPanel() {
        this.panel = new JPanel(new GridLayout(3,2));
        this.panel.add(this.nameField);
        this.panel.add(this.phoneField);
        this.panel.add(this.emailField);
        this.panel.add(this.button);
        JPanel outputPanel = new JPanel();
        outputPanel.add(this.outputArea);
        this.panel.add(outputPanel);

    }

    private void setUpElements() {
        this.nameField = new JTextField("Name - valid form : Firstname Lastname  or just Firstname");
        this.phoneField = new JTextField("Phonenumber - valid form: 01812345, 018 123456 or 018[/-]123456");
        this.emailField = new JTextField("Email - valid form: [A-Za-z0-9_.%]+@[A-Za-z0-9._\\-]+[.][a-zA-Z]");
        this.outputArea = new JTextArea("Output");


        // Listen for action
        ActionListener inputListener = e -> {
            this.nameCommand = this.nameField.getText();
            this.phoneCommand = this.phoneField.getText();
            this.emailCommand = this.emailField.getText();
            this.gotCommand = true;
        };

        this.nameField.addActionListener(inputListener);
        this.phoneField.addActionListener(inputListener);
        this.emailField.addActionListener(inputListener);

        this.button = new JButton("Commit");
        this.button.addActionListener(inputListener);

    }

}


