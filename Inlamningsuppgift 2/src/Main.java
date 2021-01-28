import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        File file = new File("text.txt");

        try {
            Scanner scanner = new Scanner(file);

            String taskOne = ".*abcdefghijklmnopqrstuvwxyz.*|.*ABCDEFGHIJKLMNOPQRSTUVWXYZ.*"; // Complete alfabetet
            String taskTwo = ".*[Oo][Ll][Aa].*"; // Where is Olas name
            String taskThree = ".*[Aa]{3,5}.*"; // 3-5 [Aa] in a row
            String taskFour = "[^A-Za-z0-9]{2,}"; // Do not match  [A-Za-z0-9]
            String taskFive = "[A-Za-z0-9_.%]+@[A-Za-z0-9._\\-]+[.][a-zA-Z]{2,3}"; // e-mail adress
            String taskSix = "[0-9]{3}[\\s/\\-]?[0-9]{5,6}"; // Phone numbers
            String taskSeven = ".*([A-Za-z])\\1{3}.*"; // 4 of the same in a row
            String taskEight = ".*[A-Za-z]{3}[/_][0-9]{3}.*"; // Registration numbers
            String taskNine = ".*\\(([A-Za-z0-9\\s]+)\\).*"; // perfect match to ( ... )
            String taskTen = ".*(if|for)(\\([A-Za-z0-9\\s].*\\)).*"; // if statement and for loops

            String myString = "";
            int rowCounter = 1;
            int taskThreeCounter = 0;
            int taskFourCounter = 0;
            int taskFiveCounter = 0;
            int taskSixCounter = 0;
            int taskSevenCounter = 0;

            while (scanner.hasNextLine()) {
                myString = scanner.nextLine();
                if (finder(myString, taskOne)) System.out.println("Row: " + rowCounter + ", Complete alfabetet found");
                if (finder(myString, taskTwo)) System.out.println("Row: " + rowCounter + ",  Olas name found");
                taskThreeCounter = countOccurrences(myString, taskThree, taskThreeCounter);
                taskFourCounter = countOccurrences(myString, taskFour, taskFourCounter);
                taskFiveCounter = countEmails(myString, taskFive, taskFiveCounter, rowCounter);
                taskSixCounter = countPhoneNumbers(myString, taskSix, taskSixCounter, rowCounter);
                taskSevenCounter = countOccurrences(myString, taskSeven, taskSevenCounter);
                if (finder(myString, taskEight))
                    System.out.println("Row: " + rowCounter + ", Registration number found");
                perfectMatchedParentheses(myString, taskNine, rowCounter);
                if (finder(myString, taskTen))
                    System.out.println("Row: " + rowCounter + ", Found if statement or for loop");
                rowCounter++;
            }

            System.out.println("\nNumber of 3-5 [Aa] in a row: " + taskThreeCounter);
            System.out.println("Number of strings not containing  [A-Za-z0-9]:  " + taskFourCounter);
            System.out.println("Number of 4 in a row: " + taskSevenCounter);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean finder(String myString, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(myString);
        return matcher.matches();
    }

    public static int countOccurrences(String myString, String pattern, int counter) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(myString);
        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                counter++;
            }
        }
        return counter;
    }

    public static int countEmails(String myString, String pattern, int emailCount, int rowCounter) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(myString);
        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                emailCount++;
                System.out.println("Row: " + rowCounter + ", Found valid emails | " + emailCount + " |  " + matcher.group());
            }
        }
        return emailCount;
    }

    public static int countPhoneNumbers(String myString, String pattern, int phoneNumberCounter, int rowCounter) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(myString);
        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                phoneNumberCounter++;
                System.out.println("Row: " + rowCounter + ", Found phone numbers | " + phoneNumberCounter + " |  " + matcher.group());
            }
        }
        return phoneNumberCounter;
    }

    public static void perfectMatchedParentheses(String myString, String pattern, int rowCounter) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(myString);
        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                String[] parentheses = matcher.group().split("(?=\\()");
                findAllPerfectMatchedParentheses(parentheses, pattern, rowCounter);
            }
        }
    }

    public static void findAllPerfectMatchedParentheses(String[] parentheses, String pattern, int rowCounter) {
        Pattern p = Pattern.compile(pattern);
        for (String myString : parentheses) {
            Matcher matcher = p.matcher(myString);
            while (matcher.find()) {
                if (matcher.group().length() != 0) {
                    String requiredString = matcher.group().substring(matcher.group().indexOf("(") + 1, matcher.group().indexOf(")"));
                    System.out.println("Row: " + rowCounter + ", Perfect Matched Parentheses: (" + requiredString + ")");
                }
            }
        }
    }
}
