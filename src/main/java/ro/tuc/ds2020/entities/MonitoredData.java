package ro.tuc.ds2020.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity(name = "MonitoredData")
public class MonitoredData {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column
    private Date startTime;
    @Column
    private Date endTime;
    @Column
    private String activity;


    public MonitoredData() {
    }

    public MonitoredData(UUID id, Patient patient, Date startTime, Date endTime, String activity) {
        this.id = id;
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public MonitoredData(Patient patient, Date startTime, Date endTime, String activity) {
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public MonitoredData(UUID id, Date startTime, Date endTime, String activity) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public MonitoredData(Date startTime, Date endTime, String activity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getStart() {
        return startTime;
    }

    public void setStart(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEnd() {
        return endTime;
    }

    public void setEnd(Date endTime) {
        this.endTime = endTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}