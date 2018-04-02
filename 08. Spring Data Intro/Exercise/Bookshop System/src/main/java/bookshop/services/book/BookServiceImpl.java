package bookshop.services.book;

import bookshop.models.Book;
import bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    final private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void newBook(final Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void newBooks(final Iterable<Book> books) {
        this.bookRepository.saveAll(books);
    }

    @Override
    public long getBooksCount() {
        return this.bookRepository.count();
    }

    @Override
    public List<String> getTitlesOfAllBooksReleasedAfter(final Date date) {
        return this.bookRepository
                .getBooksByReleaseDateAfter(date)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksTitleReleaseDateAndCopiesByAuthorNames(final String firstName, final String lastName) {
        return this.bookRepository
                .getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName)
                .stream()
                .map(book -> String.format("%s - %s - %d",
                        book.getTitle(), book.getReleaseDate().toString(), book.getCopies()))
                .collect(Collectors.toList());
    }
}
