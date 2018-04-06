package bookshop.services.book;

import bookshop.models.Book;
import bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
