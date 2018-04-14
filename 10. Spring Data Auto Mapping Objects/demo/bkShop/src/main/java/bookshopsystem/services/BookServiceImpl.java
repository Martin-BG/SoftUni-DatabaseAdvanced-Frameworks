package bookshopsystem.services;

import bookshopsystem.models.entity.Book;
import bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveIntoDb(List<Book> books) {
        this.bookRepository.save(books);
    }

    @Override
    public List<String> allTitlesAfterYear(Date year) {

        return this.bookRepository.findAllByReleaseDateAfter(year)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }
}
