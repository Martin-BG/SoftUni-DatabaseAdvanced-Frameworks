package app.retake.repositories;

import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Animal findByPassport_SerialNumber(final String passportNumber);

    @Query("SELECT new app.retake.domain.dto.AnimalsJSONExportDTO" +
            "(a.passport.ownerName, a.name, a.age, a.passport.serialNumber, a.passport.registrationDate) " +
            "FROM Animal AS a " +
            "WHERE a.passport.ownerPhoneNumber = :number " +
            "ORDER BY a.age, a.passport.serialNumber")
    List<AnimalsJSONExportDTO> exportAnimalsByOwnerPhoneNumber(@Param("number") String number);
//    List<Animal> findAllByPassport_OwnerPhoneNumberOrderByAgeAscPassportAsc(final String phoneNumber);
}
