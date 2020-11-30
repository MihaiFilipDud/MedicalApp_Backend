package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;

public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {
        return new PatientDTO(patient.getId(), patient.getName(), patient.getAddress(), patient.getAge(), patient.getGender(), patient.getMedicalRecord(), patient.getAccount());
    }



    public static Patient toEntity(PatientDTO patientDTO){
        return new Patient(patientDTO.getName(), patientDTO.getAddress(), patientDTO.getAge(), patientDTO.getGender(), patientDTO.getAccount(), patientDTO.getMedicalRecord(), patientDTO.getCaregiver());
    }


}
