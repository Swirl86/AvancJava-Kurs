import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MainToDo {

    public static void main(String[] args) {
        // Task 1
        String lineSystemOut ="System.out.println(Hej Hej Ola);";
        String patternSystemOut = "System\\.out\\.println(\\s+)?((\\s+)?([\"]?(\\s+)?(.+)?(\\s+)?[\"]?)(\\s+)?\\)(\\s+)?)(\\s+)?;";

        //Task 2
        String lineIfElse ="if(Villkor){HejHoppOla;} else {HoppHejOla;}";
        String patternIfElse = "if(\\s+)?\\([A-Za-z0-9][a-z0-9]+\\)(\\s+)?\\{[A-Za-z1-9\\s]+\\;\\}(\\s+)?[A-Za-z0-9]+(\\s+)?\\{[A-Za-z1-9\\s]+\\;\\}";

        //Task 3
        String lineDoWhile = "do {\n" +
                "System.out.println(i);\n" +
                "}\n" +
                "while (i < 5);";
        String patternDoWhile = "do(\\s)?\\{[\\n\\r]?(\\s)*[A-Za-z1-9\\s.\\(\\)\\W]+;(\\s)*[\\n\\r]?\\}[\\n\\r]?while(\\s)*\\((\\s)*[a-z1-9\\s\\W]+(\\s)*\\);";

        //Task 4
        String lineForLoop = "for(int i = 0; i < 10; i++) {\n" +
                "    System.out.println(\"Hejsan\");\n" +
                "}";
        String patternForLoop = "for(\\s+)?\\((\\s+)?int(\\s+)?[a-zA-Z]+(\\s+)?=(\\s+)?[0-9]+(\\s+)?;(\\s+)?[a-zA-z]+(\\s+)?[<>=]{1,2}(\\s+)?[0-9]" +
                "+(\\s+)?;(\\s+)?[a-zA-Z]+(\\s+)?[+-]{2}(\\s+)?\\)(\\s+)\\{(\n+)(\\s+)[A-Za-z1-9\\s.\\(\\)\\W]+(\\n)?\\}";


        System.out.println("------------");
        finder(lineIfElse, patternIfElse); //Task 1
        System.out.println("\n------------");
        finder(lineDoWhile, patternDoWhile); //Task 2
        System.out.println("\n------------");
        finder(lineSystemOut, patternSystemOut); //Task 3
        System.out.println("\n------------");
        finder(lineForLoop, patternForLoop); //Task 4
        System.out.println("\n------------");



    }
    public static void finder (String line, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(line);
        while(matcher.find()){
            if(matcher.group().length() != 0 ) {
                System.out.print("Found: " + matcher.group());

            }
        }
    }
}

