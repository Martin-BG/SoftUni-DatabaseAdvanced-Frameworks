package bookshop.services.book;

import bookshop.dto.book.ReducedBook;
import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;
import bookshop.models.Book;
import bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    public List<String> getBookTitleByAgeRestriction(final AgeRestriction ageRestriction) {
        return this.bookRepository
                .getBooksByAgeRestrictionIs(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookTitleOfGoldenEditionBookWithLessThen5000Copies() {
        return this.bookRepository
                .getBooksByEditionTypeIsAndCopiesLessThan(EditionType.GOLD, 5000)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookTitleAndPriceForBooksWithPriceUnder5AndHigherThan40() {
        return this.bookRepository
                .getBooksByPriceIsLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40))
                .stream()
                .map(book -> String.format("%s - $%.2f", book.getTitle(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookTitleOfBooksNotReleasedOnGivenYear(final Integer year) {
        return this.bookRepository
                .getBooksByReleaseDateNot_Year(year)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookTitleEditionTypeAndPriceForBooksReleasedBeforeDate(final LocalDate date) {
        return this.bookRepository
                .getBooksByReleaseDateBefore(date)
                .stream()
                .map(book -> String.format("%s - %s - $%.2f", book.getTitle(), book.getEditionType(), book.getPrice()))
                .collect(Collectors.toList());

    }

    @Override
    public List<String> getBookTitleForBookTitlesContainingText(final String text) {
        return this.bookRepository
                .getBooksByTitleContains(text)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookTitleAndAuthorForAuthorsLastNameStartsWith(final String starting) {
        return this.bookRepository
                .getBooksByAuthorLastNameStartsWith(starting)
                .stream()
                .map(book -> String.format("%s (%s %s)", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getCountOfBooksWithTitleLongerThan(final Integer length) {
        return this.bookRepository
                .getCountOfBooksWithTitleLongerThan(length);
    }

    @Override
    public String getBookDetailsByTitle(final String title) {
        ReducedBook reducedBook = this.bookRepository.getBookByTitle(title);
        return (reducedBook == null) ? "Book Not Found" : reducedBook.toString();
    }

    @Override
    public Integer increaseCopiesForBooksReleasedAfterDate(final LocalDate startDate, final Integer copiesToAdd) {
        return this.bookRepository.increaseCopiesForBooksReleasedAfterDate(startDate, copiesToAdd);
    }

    @Override
    public Integer removeBooksWithCopiesLessThan(final Integer minCopies) {
        return this.bookRepository.removeBooksWithCopiesLessThan(minCopies);
    }
}
