package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.MonitoredData;

import java.util.UUID;

public interface MonitoredDataRepository extends JpaRepository<MonitoredData, UUID> {
}
