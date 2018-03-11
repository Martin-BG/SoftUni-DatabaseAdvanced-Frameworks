import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Pr07BoundedNumbers {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] bounds = Arrays
                    .stream(reader.readLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            System.out.println(String.join(" ",
                    Arrays.stream(reader.readLine().split("\\s+"))
                            .map(Integer::valueOf)
                            .filter(n -> n >= bounds[0] && n <= bounds[1])
                            .map(String::valueOf)
                            .collect(Collectors.toList())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
