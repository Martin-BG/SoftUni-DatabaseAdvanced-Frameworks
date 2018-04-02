package user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.models.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
