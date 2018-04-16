package product_shop.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import product_shop.model.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users ORDER BY RAND () LIMIT 1", nativeQuery = true)
    User getRandomEntity();

    @Query("" +
            "SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller.id = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.lastName, u.firstName")
    List<User> getAllBySellContainsProduct_Buyer();
}
