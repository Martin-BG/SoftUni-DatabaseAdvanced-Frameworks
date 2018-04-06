package bookshop.runners;

import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;
import bookshop.models.Author;
import bookshop.models.Book;
import bookshop.models.Category;
import bookshop.services.author.AuthorService;
import bookshop.services.book.BookService;
import bookshop.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Controller
public class ConsoleRunner implements CommandLineRunner {

    private static final String ENCODING = "UTF8";
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static final String AUTHORS_FILE = "authors.txt";
    private static final String CATEGORIES_FILE = "categories.txt";
    private static final String BOOKS_FILE = "books.txt";
    private static final String INPUT_SEPARATOR = "\\s+";
    private static final String NAME_DELIMITER = " ";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(final AuthorService authorService,
                         final BookService bookService,
                         final CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(final String... args) {
        if (this.bookService.getBooksCount() == 0L) {   // Load data from external files if base is empty
            seedDatabase();
        }

    }

    private void seedDatabase() {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(RESOURCES_PATH + AUTHORS_FILE), ENCODING))) {
            br.lines()
                    .map(line -> line.split(INPUT_SEPARATOR))
                    .forEach(names -> {
                        Author author = new Author();
                        author.setFirstName(names[0]);
                        author.setLastName(names[1]);
                        this.authorService.registerAuthor(author);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (final BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(RESOURCES_PATH + CATEGORIES_FILE), ENCODING))) {
            br.lines()
                    .filter(cat -> cat != null && !cat.isEmpty())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName.trim());
                        this.categoryService.newCategory(category);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (final BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(RESOURCES_PATH + BOOKS_FILE), ENCODING))) {

            // if UTF-8 BOM file
            br.mark(4);
            if ('\ufeff' != br.read()) {
                br.reset(); // not the BOM marker
            }

            br.lines()
                    .map(line -> line.split(INPUT_SEPARATOR))
                    .forEach(data -> {
                        Book book = new Book();
                        book.setAuthor(this.authorService.getRandomAuthor());
                        book.setEditionType(EditionType.values()[Integer.parseInt(data[0])]);
                        book.setReleaseDate(LocalDate.parse(data[1], DATE_FORMAT));
                        book.setCopies(Integer.parseInt(data[2]));
                        book.setPrice(new BigDecimal(data[3]));
                        book.setAgeRestriction(AgeRestriction.values()[Integer.parseInt(data[4])]);
                        book.setTitle(String.join(NAME_DELIMITER, Arrays.copyOfRange(data, 5, data.length)));
                        book.setCategories(this.categoryService.getRandomCategories());
                        this.bookService.newBook(book);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }
}
