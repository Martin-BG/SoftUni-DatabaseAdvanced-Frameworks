package bookshop.services.author;

import bookshop.models.Author;

import java.util.Date;
import java.util.List;

public interface AuthorService {

    void registerAuthor(Author author);

    void registerAuthors(Iterable<Author> authors);

    Author getRandomAuthor();

    List<String> getAuthorsWithBooksReleasedBeforeDate(Date date);

    List<String> getAuthorsByBooksCount();
}
