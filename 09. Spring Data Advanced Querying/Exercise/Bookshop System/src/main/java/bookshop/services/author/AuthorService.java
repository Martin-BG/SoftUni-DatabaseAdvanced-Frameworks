package bookshop.services.author;

import bookshop.models.Author;
import org.springframework.stereotype.Component;

@Component
public interface AuthorService {

    void registerAuthor(Author author);

    void registerAuthors(Iterable<Author> authors);

    Author getRandomAuthor();

}
