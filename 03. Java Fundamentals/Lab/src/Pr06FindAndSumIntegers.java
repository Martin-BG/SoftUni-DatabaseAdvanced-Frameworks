import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

public class Pr06FindAndSumIntegers {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            Optional<Long> sum = Arrays.stream(reader.readLine().split("\\s+"))
                    .filter(n -> n.matches("[+-]?\\d+"))
                    .map(Long::valueOf)
                    .reduce((n1, n2) -> n1 + n2);

            System.out.println(sum.isPresent() ? String.format("%d", sum.get()) : "No match");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
