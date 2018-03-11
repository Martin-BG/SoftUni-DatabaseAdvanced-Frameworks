import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pr03FirstName {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Stream<String> namesStream = Arrays.stream(reader.readLine().split("\\s+"));

            HashSet<Character> chars = Arrays.stream(reader.readLine().split("\\s+"))
                    .map(s -> s.toUpperCase().charAt(0))
                    .collect(Collectors.toCollection(HashSet::new));

            System.out.println(namesStream
                    .filter(name -> chars.contains(name.toUpperCase().charAt(0)))
                    .sorted()
                    .findFirst()
                    .orElse("No match"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
