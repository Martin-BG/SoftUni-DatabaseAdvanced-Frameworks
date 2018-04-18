package car_dealer.persistance.repository;

import car_dealer.model.dto.view.SupplierViewDto;
import car_dealer.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT new car_dealer.model.dto.view.SupplierViewDto( " +
            "s.id, s.name, COUNT(s)) " +
            "FROM Supplier AS s " +
            "JOIN Part AS p " +
            "ON s.id = p.supplier.id " +
            "WHERE s.isImporter = false " +
            "GROUP BY s.id")
    List<SupplierViewDto> getLocalSuppliers();
}
