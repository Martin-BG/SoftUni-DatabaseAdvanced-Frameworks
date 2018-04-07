package bookshop.repositories;

import bookshop.dto.book.ReducedBook;
import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;
import bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBooksByAgeRestrictionIs(final AgeRestriction ageRestriction);

    List<Book> getBooksByEditionTypeIsAndCopiesLessThan(final EditionType editionType, final int copies);

    List<Book> getBooksByPriceIsLessThanOrPriceGreaterThan(final BigDecimal lowerThanPrice, final BigDecimal higherThanPrice);

    @Query("SELECT b FROM Book AS b WHERE FUNCTION('YEAR', b.releaseDate) <> :year")
    List<Book> getBooksByReleaseDateNot_Year(@Param("year") final int year);

    List<Book> getBooksByReleaseDateBefore(final LocalDate releaseDate);

    List<Book> getBooksByTitleContains(final String text);

    @Query("SELECT b FROM Book AS b WHERE b.author.lastName LIKE :start%")
    List<Book> getBooksByAuthorLastNameStartsWith(@Param("start") final String start);

    @Query("SELECT COUNT(b) FROM Book AS b WHERE LENGTH(b.title) > :length ")
    Integer getCountOfBooksWithTitleLongerThan(@Param("length") final int length);

    /*    @Query("SELECT new bookshop.dto.book.ReducedBook(b.title, b.editionType, b.ageRestriction, b.price) " +
                "FROM Book AS b " +
                "WHERE b.title = :title")
        ReducedBook getBookByTitle(@Param("title")final String title);*/
    ReducedBook getBookByTitle(final String title);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :cnt WHERE b.releaseDate > :date")
    Integer increaseCopiesForBooksReleasedAfterDate(@Param("date") LocalDate startDate, @Param("cnt") int copiesToAdd);

    @Modifying
    @Query("DELETE FROM Book AS b WHERE b.copies < :copies")
    Integer removeBooksWithCopiesLessThan(@Param("copies") int minCopies);
}
