package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.util.List;
import java.util.UUID;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID> {

    List<MedicationPlan> findMedicationPlansByPatient_Id(UUID patiendId);
}
