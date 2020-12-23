package ro.tuc.ds2020.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoredDataDTO {

    private UUID id;
    private UUID patient_id;
    private Date start;
    private Date end;
    private String activity;

    public MonitoredDataDTO(UUID patient_id, Date start, Date end, String activity) {
        this.patient_id = patient_id;
        this.start = start;
        this.end = end;
        this.activity = activity;
    }

    public long timeDif() {
        return Math.abs(end.getTime() - start.getTime());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(UUID patient_id) {
        this.patient_id = patient_id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}

