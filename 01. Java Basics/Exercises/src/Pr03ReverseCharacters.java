import java.util.Scanner;

public class Pr03ReverseCharacters {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        System.out.println(sb
                .append(scanner.next().charAt(0))
                .append(scanner.next().charAt(0))
                .append(scanner.next().charAt(0))
                .reverse()
                .toString()
        );
    }
}
