package app.exam.repository;

import app.exam.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
@Transactional
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    Item findByName(String name);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Item i set i.price = :price where i.name = :itemName")
    void updateItemPrice(@Param(value = "itemName") String itemName, @Param(value = "price") BigDecimal price);
}
