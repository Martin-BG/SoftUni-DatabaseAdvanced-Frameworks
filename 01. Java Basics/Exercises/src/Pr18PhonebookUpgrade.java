import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Pr18PhonebookUpgrade {
    public static void main(String[] args) throws IOException {

        Map<String, String> phonebook = new TreeMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] commands = reader.readLine().trim().split("\\s+");

            if ("END".equalsIgnoreCase(commands[0])) {
                break;
            }

            switch (commands[0].toUpperCase()) {
            case "A":
                phonebook.put(commands[1], commands[2]);
                break;
            case "S":
                if (phonebook.containsKey(commands[1])) {
                    System.out.printf("%s -> %s%n", commands[1], phonebook.get(commands[1]));
                } else {
                    System.out.printf("Contact %s does not exist.%n", commands[1]);
                }
                break;
            case "LISTALL":
                phonebook.forEach((key, value) -> System.out.printf("%s -> %s%n", key, value));
                break;
            default:
                break;
            }
        }
    }
}
