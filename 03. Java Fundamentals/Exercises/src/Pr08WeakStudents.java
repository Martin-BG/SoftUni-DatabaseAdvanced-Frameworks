import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Pr08WeakStudents {

    public static void main(String[] args) {

        streamOfInputData()
                .filter(st -> Stream
                        .of(Integer.parseInt(st[2]), Integer.parseInt(st[3]),
                                Integer.parseInt(st[4]), Integer.parseInt(st[5]))
                        .filter(mark -> mark <= 3)
                        .count() >= 2)
                .forEach(student -> System.out.printf("%s %s%n", student[0], student[1]));
    }

    private static Stream<String[]> streamOfInputData() {
        List<String> input = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String line;

            while (!"END".equalsIgnoreCase(line = reader.readLine().trim())) {
                input.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return input.stream().map(str -> str.split("\\s++"));
    }
}
