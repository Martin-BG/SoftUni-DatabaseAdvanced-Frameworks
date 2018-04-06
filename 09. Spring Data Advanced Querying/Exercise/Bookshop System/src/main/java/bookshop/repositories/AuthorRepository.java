package bookshop.repositories;

import bookshop.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> getAllByFirstNameEndingWith(final String ending);

    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName, ' - ', SUM(b.copies)) " +
            "FROM Author AS a " +
            "JOIN a.books AS b " +
            "GROUP BY a.id " +
            "ORDER BY SUM(b.copies) DESC ")
    List<Object> getAuthorsByBooksCopies();
}
