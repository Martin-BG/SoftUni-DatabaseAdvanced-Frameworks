import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;

public class Pr05MinEvenNumber {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            OptionalDouble min = Arrays.stream(reader.readLine().split("\\s+"))
                    .filter(n -> !n.isEmpty())
                    .mapToDouble(Double::parseDouble)
                    .filter(n -> n % 2 == 0)
                    .min();

            if (min.isPresent()) {
                System.out.printf("%.2f%n", min.getAsDouble());
            } else {
                System.out.println("No match");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
