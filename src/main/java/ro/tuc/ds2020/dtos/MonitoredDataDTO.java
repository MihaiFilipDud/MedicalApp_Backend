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

}

