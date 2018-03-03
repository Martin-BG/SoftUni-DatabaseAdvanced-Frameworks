package Pr08BookLibrary;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {

    private final String name;
    private final Collection<Book> books;

    public Library(String name, Collection<Book> books) {
        this.name = name;
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public String getPriceByAuthor() {
        StringBuilder sb = new StringBuilder();

        this.books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingDouble(Book::getPrice)))
                .entrySet().stream().sorted(authorsByPriceAndNameComparator())
                .forEach(a -> sb.append(String.format("%s -> %.2f%n", a.getKey(), a.getValue())));

        return sb.toString();
    }

    private Comparator<Map.Entry<String, Double>> authorsByPriceAndNameComparator() {
        return (a1, a2) -> {
            int res = a2.getValue().compareTo(a1.getValue());
            if (res == 0) {
                res = a1.getKey().compareTo(a2.getKey());
            }
            return res;
        };
    }
}
