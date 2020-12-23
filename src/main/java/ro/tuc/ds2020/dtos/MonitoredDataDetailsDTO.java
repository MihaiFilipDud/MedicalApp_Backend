package ro.tuc.ds2020.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;

import java.util.Date;
import java.util.UUID;

@Data

public class MonitoredDataDetailsDTO {

    private PatientDTO patient;
    private CaregiverDTO caregiver;
    private Date start;
    private Date end;
    private String activity;

    public MonitoredDataDetailsDTO(PatientDTO patient, CaregiverDTO caregiver, Date start, Date end, String activity) {
        this.patient = patient;
        this.caregiver = caregiver;
        this.start = start;
        this.end = end;
        this.activity = activity;
    }

    public MonitoredDataDetailsDTO() {
    }

    public long timeDif() {
        return Math.abs(end.getTime() - start.getTime());
    }

}
