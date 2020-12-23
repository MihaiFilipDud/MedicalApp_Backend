package ro.tuc.ds2020.hessian_rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import ro.tuc.ds2020.entities.MedicationStatus;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.repositories.MedicationStatusRepository;

@Configuration
public class HessianConfig {

    @Autowired
    public MedicationPlanRepository medicationPlanRepository;
    @Autowired
    public MedicationStatusRepository medicationStatusRepository;
    @Autowired
    public MedicationRepository medicationRepository;

    public HessianConfig() {
    }

    @Bean(name = "/hellohessian")
    RemoteExporter sayHelloServiceHessian() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(new HelloWorldImpl());
        exporter.setServiceInterface(HelloWorld.class);
        return exporter;
    }

    @Bean(name = "/medPlanTransfer")
    RemoteExporter medPlanTransferService() {
        System.out.println(medicationPlanRepository);
        System.out.println(medicationStatusRepository);
        System.out.println(medicationRepository);
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(new MedicationPlanTransferImpl(medicationPlanRepository, medicationStatusRepository, medicationRepository));
        exporter.setServiceInterface(MedicationPlanTransfer.class);
        return exporter;
    }
}
