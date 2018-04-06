package bookshop.repositories;

import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;
import bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query(value = "SELECT b FROM Book AS b WHERE FUNCTION('YEAR', b.releaseDate) <> :year")
    List<Book> getBooksByReleaseDateNot_Year(@Param("year") final int year);

    List<Book> getBooksByReleaseDateBefore(final LocalDate releaseDate);
}
