import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pr08BookStore {

    public static void main(String[] args) {

        Stream.of(
                new Book("Vinetu3", 20, "Karl Mai"),
                new Book("Vinetu1", 20, "Karl Mai"),
                new Book("Vinetu2", 15, "Karl Mai"),
                new Book("Sherlock Holmes", 12, "Arthur C. Doyl"),
                new Book("The Lost World", 43, "Arthur C. Doyl"),
                new Book("Hunger games", 150, "Suzan Colins"))
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingDouble(Book::getPrice)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry::getKey))
                .forEach(a -> System.out.printf("%s=%.2f%n", a.getKey(), a.getValue()));
    }

    private static class Book {

        private final String name;
        private final double price;
        private final String author;

        Book(String name, double price, String author) {
            this.name = name;
            this.price = price;
            this.author = author;
        }

        double getPrice() {
            return this.price;
        }

        String getAuthor() {
            return this.author;
        }
    }
}
