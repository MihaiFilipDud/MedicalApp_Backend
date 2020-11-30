package ro.tuc.ds2020.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
    List<Patient> findByName(String name);


    /**
     * Example: Write Custom Query
     */


    //    @Query(value = "")
//    Optional<Person> update(@Param("id") UUID id);
//

    void deleteById(UUID id);




    List<Patient> findByCaregiver_Id(UUID caregiverID);
}