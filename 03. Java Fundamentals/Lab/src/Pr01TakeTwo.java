import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr01TakeTwo {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(String.join(" ",
                    Arrays.stream(reader.readLine().trim().split("\\s+"))
                            .mapToInt(Integer::parseInt)
                            .filter(num -> num >= 10 && num <= 20)
                            .distinct()
                            .limit(2)
                            .mapToObj(String::valueOf)
                            .toArray(String[]::new)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
