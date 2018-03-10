import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pr10GroupByGroup {

    public static void main(String[] args) {

        streamPersons()
                .collect(Collectors.groupingBy(Person::getGroup))
                .forEach((key, value) -> {
                    List<String> names = value.stream()
                            .map(Person::getName)
                            .collect(Collectors.toList());
                    System.out.printf("%d - %s%n", key, String.join(", ", names));
                });
    }

    private static Stream<Person> streamPersons() {
        List<Person> input = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String line;

            while (!"END".equalsIgnoreCase(line = reader.readLine().trim())) {
                String[] tokens = line.split("\\s+");

                String name = tokens[0] + " " + tokens[1];
                int group = Integer.parseInt(tokens[2]);

                input.add(new Person(name, group));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return input.stream();
    }

    private static class Person {

        private final String name;
        private final int group;

        private Person(String name, int group) {
            this.name = name;
            this.group = group;
        }

        private String getName() {
            return this.name;
        }

        private int getGroup() {
            return this.group;
        }
    }
}
