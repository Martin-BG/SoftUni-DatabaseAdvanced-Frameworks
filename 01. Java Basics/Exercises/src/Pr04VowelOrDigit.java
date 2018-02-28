import java.util.Scanner;

public class Pr04VowelOrDigit {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String symbol = scanner.nextLine().trim().toUpperCase();

        if (Character.isDigit(symbol.charAt(0))) {
            System.out.println("digit");
        } else if ("AEIOU".contains(symbol)) {
            System.out.println("vowel");
        } else {
            System.out.println("other");
        }
    }
}
