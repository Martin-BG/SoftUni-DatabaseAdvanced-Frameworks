package bookshop.services.author;

import bookshop.models.Author;
import bookshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    final private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void registerAuthor(final Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public void registerAuthors(final Iterable<Author> authors) {
        this.authorRepository.saveAll(authors);
    }

    @Override
    public Author getRandomAuthor() {
        long count = this.authorRepository.count();
        if (count < 1L) {
            throw new RuntimeException("No authors in database");
        }

        List<Author> authors = this.authorRepository.findAll();
        Random random = new Random();
        int index = (int) ((random.nextLong() & Long.MAX_VALUE) % count); // Ensure positive index (random can produce negative numbers)
        return authors.get(index);
    }

    @Override
    public List<String> getAuthorsWithBooksReleasedBeforeDate(final Date date) {
        return this.authorRepository
                .findAuthorsWithBooksReleasedBeforeDate(date)
                .stream()
                .map(author -> String.format("%s %s", author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAuthorsByBooksCount() {
        return this.authorRepository
                .findAuthorsByOrderByBooksDesc()
                .stream()
                .map(author -> String.format("%s %s - %d books",
                        author.getFirstName(), author.getLastName(), author.getBooks().size()))
                .collect(Collectors.toList());

        // Solution with local sort of all authors
/*        return this.authorRepository
                .findAll()
                .stream()
                .sorted((a1, a2) -> Integer.compare(a2.getBooks().size(), a1.getBooks().size()))
                .map(author -> String.format("%s %s - %d books",
                        author.getFirstName(), author.getLastName(), author.getBooks().size()))
                .collect(Collectors.toList());*/
    }
}
