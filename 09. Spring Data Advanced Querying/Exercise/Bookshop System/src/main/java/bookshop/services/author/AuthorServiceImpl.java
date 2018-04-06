package bookshop.services.author;

import bookshop.models.Author;
import bookshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<String> getAuthorNamesWithFirstNameEndingWith(final String ending) {
        return this.authorRepository
                .getAllByFirstNameEndingWith(ending)
                .stream()
                .map(author -> String.format("%s %s", author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());
    }
}
