package hiberspring.repositories;

import hiberspring.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Branch getBranchByName(String name);

    @Query(value = "SELECT b.name, t.name, sum(p.countOfClients) AS clientsNum" +
            "	FROM Product AS p " +
    		"	RIGHT JOIN p.branch AS b " +
            "  	INNER JOIN b.town AS t" +
            "	GROUP BY b, t" +
            "	ORDER BY clientsNum DESC")
    List<Object> getTopBranches();
}
