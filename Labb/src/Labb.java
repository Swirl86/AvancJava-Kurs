import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Labb {
    private
    Gui gui;
    boolean run;

    public Labb() {
        this.gui = new Gui();
        this.run = true;
        start();
    }

    public void start() {
        //initialize variables
        String name = null;
        String phone = null;
        String email = null;

        while (this.run) {
            //Fetch input
            if (name == null || phone == null || email == null) {
                name = this.gui.getNameCommand();
                phone = this.gui.getPhoneCommand();
                email = this.gui.getEmailCommand();
                try { // Loop slower
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                this.run = false; // dont fetch more values
                // work with regex
                System.out.println("Name: " + name);
                System.out.println("Phone: " + phone);
                System.out.println("Email: " + email);
                checkPatterns(name, phone, email);
            }
        }
    }

    private void checkPatterns(String name, String phone, String email) {
        String namePattern = "[A-Za-z]{2,}\\s?([A-Za-z]{2,})?";
        String phonePattern = "[0-9]{3}[\\s/\\-]?[0-9]{5,6}";
        String emailPattern = "[A-Za-z0-9_.%]+@[A-Za-z0-9._\\-]+[.][a-zA-Z]{2,3}";

        Pattern p = Pattern.compile(namePattern);
        Matcher matcher = p.matcher(name);
        boolean nameMatch = matcher.matches();

        p = Pattern.compile(phonePattern);
        matcher = p.matcher(phone);
        boolean phoneMatch = matcher.matches();

        p = Pattern.compile(emailPattern);
        matcher = p.matcher(email);
        boolean emailMatch = matcher.matches();

        String output = "";

        if(nameMatch && phoneMatch && emailMatch){
            output = "Name: " + name + " is valid.\n" +
                    "Phone number: " + phone + " is valid.\n" +
                    "Email: " + email + " is valid.";
        } else {
            output = "Something is not valid! . . .\n" +
                    "Name is valid? " + nameMatch + "\n" +
                    "Phone number is valid? " + phoneMatch + "\n" +
                    "Email is valid? " + emailMatch;
        }

        this.gui.setOutputArea(output);
    }
}
