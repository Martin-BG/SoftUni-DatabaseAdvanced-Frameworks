import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr10IndexOfLetters {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] charArrOne = reader.readLine().toCharArray();

        for (char c : charArrOne) {
            System.out.printf("%c -> %d%n", c, c - 'a');
        }
    }
}
