package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationStatus;

import java.util.UUID;

@Repository
public interface MedicationStatusRepository extends JpaRepository<MedicationStatus, UUID> {
}
