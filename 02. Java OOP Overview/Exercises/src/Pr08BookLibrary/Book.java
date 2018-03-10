package Pr08BookLibrary;

public class Book {

    private final String title;
    private final String author;
    private final String publisher;
    private final String releaseDate;
    private final String ISBN;
    private final double price;

    public Book(String title, String author, String publisher,
                String releaseDate, String ISBN, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.ISBN = ISBN;
        this.price = price;
    }

    public String getAuthor() {
        return this.author;
    }

    public double getPrice() {
        return this.price;
    }
}
