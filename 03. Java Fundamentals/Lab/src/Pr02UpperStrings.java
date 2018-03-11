import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Pr02UpperStrings {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(String.join(" ",
                    Arrays.stream(reader.readLine().split("\\s+"))
                            .map(String::toUpperCase)
                            .collect(Collectors.toList())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
