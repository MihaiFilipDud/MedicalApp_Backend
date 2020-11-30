package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;

public class CaregiverBuilder {

    private CaregiverBuilder() {
    }



    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {
        return new CaregiverDTO(caregiver.getId(), caregiver.getName(), caregiver.getAddress(), caregiver.getAge(), caregiver.getGender(), caregiver.getAccount());
    }


    public static Caregiver toEntity(CaregiverDTO caregiverDTO){
        return new Caregiver(caregiverDTO.getName(), caregiverDTO.getAddress(), caregiverDTO.getAge(), caregiverDTO.getGender(), caregiverDTO.getAccount());
    }



}
