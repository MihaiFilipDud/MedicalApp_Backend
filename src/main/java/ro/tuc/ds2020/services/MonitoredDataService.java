package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MonitoredDataDTO;
import ro.tuc.ds2020.dtos.builders.MonitoredDataBuilder;
import ro.tuc.ds2020.entities.MonitoredData;
import ro.tuc.ds2020.entities.MonitoredData;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.MonitoredDataRepository;
import ro.tuc.ds2020.repositories.MonitoredDataRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class MonitoredDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredDataService.class);
    private final MonitoredDataRepository monitoredDataRepository;

    @Autowired
    public MonitoredDataService(MonitoredDataRepository monitoredDataRepository) {
        this.monitoredDataRepository = monitoredDataRepository;
    }

    public List<MonitoredDataDTO> findMonitoredDatas() {
        List<MonitoredData> monitoredDataList = monitoredDataRepository.findAll();
        return monitoredDataList.stream()
                .map(MonitoredDataBuilder::toMonitoredDataDTO)
                .collect(Collectors.toList());
    }

    public MonitoredDataDTO findMonitoredDataById(UUID id) {
        Optional<MonitoredData> prosumerOptional = monitoredDataRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("monitoredData with id {} was not found in db", id);
            throw new ResourceNotFoundException(MonitoredData.class.getSimpleName() + " with id: " + id);
        }
        return MonitoredDataBuilder.toMonitoredDataDTO(prosumerOptional.get());
    }

    public UUID insert(MonitoredDataDTO monitoredDataDTO) {
        MonitoredData monitoredData = MonitoredDataBuilder.toEntity(monitoredDataDTO);
        monitoredData = monitoredDataRepository.save(monitoredData);
        LOGGER.debug("monitoredData with id {} was inserted in db", monitoredData.getId());
        System.out.println("test");
        return monitoredData.getId();
    }


    public void deleteById(UUID id) {
        monitoredDataRepository.deleteById(id);
    }
}
