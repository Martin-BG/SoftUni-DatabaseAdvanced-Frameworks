package hiberspring.repositories;

import hiberspring.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    Town getTownByName(String name);

    @Query(value =  "SELECT t, sum(p.countOfClients) AS clients" +
            "	FROM Product AS p " +
    		"	INNER JOIN p.branch AS b" + 
    		"	RIGHT JOIN b.town AS t" +
            "	GROUP BY t" +
            "	ORDER BY clients DESC")
    List<Object> getTownWithClients();
}
