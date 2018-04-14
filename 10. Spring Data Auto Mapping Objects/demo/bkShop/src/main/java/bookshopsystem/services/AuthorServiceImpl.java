package bookshopsystem.services;

import bookshopsystem.models.entity.Author;
import bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private AuthorService authorService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository,
                             AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @Override
    public void saveAuthorIntoDb(Author author) {
        this.authorRepository.saveAndFlush(author);
    }

    @Override
    public void saveAuthorIntoDb(List<Author> authors) {
        this.authorRepository.save(authors);
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }
}
