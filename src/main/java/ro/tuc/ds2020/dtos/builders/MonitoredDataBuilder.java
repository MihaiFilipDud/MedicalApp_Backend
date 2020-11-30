package ro.tuc.ds2020.dtos.builders;


import ro.tuc.ds2020.dtos.MonitoredDataDTO;
import ro.tuc.ds2020.entities.MonitoredData;
import ro.tuc.ds2020.entities.Patient;

public class MonitoredDataBuilder {
    private MonitoredDataBuilder() {
    }

    public static MonitoredDataDTO toMonitoredDataDTO(MonitoredData monitoredData) {
        return new MonitoredDataDTO(monitoredData.getId(), monitoredData.getPatient().getId(), monitoredData.getStart(), monitoredData.getEnd(), monitoredData.getActivity());
    }



    public static MonitoredData toEntity(MonitoredDataDTO monitoredDataDTO){
        return new MonitoredData(monitoredDataDTO.getId(), new Patient(monitoredDataDTO.getPatient_id()), monitoredDataDTO.getStart(), monitoredDataDTO.getEnd(), monitoredDataDTO.getActivity());
    }
}
