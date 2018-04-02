package bookshop.services.book;

import bookshop.models.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void newBook(Book book);

    void newBooks(Iterable<Book> books);

    long getBooksCount();

    List<String> getTitlesOfAllBooksReleasedAfter(Date date);

    List<String> getBooksTitleReleaseDateAndCopiesByAuthorNames(String firstName, String lastName);
}
