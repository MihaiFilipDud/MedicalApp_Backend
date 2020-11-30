package ro.tuc.ds2020.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.MonitoredDataDTO;
import ro.tuc.ds2020.dtos.MonitoredDataDetailsDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.MonitoredData;
import ro.tuc.ds2020.entities.Patient;

import java.util.ArrayList;
import java.util.List;
//import ro.tuc.ds2020.entities.MonitoredData;

@Component
public class MonitoredDataAcquisition {

    public static final String  QUEUE = "activity_queue";
    private final MonitoredDataService monitoredDataService;
    private final PatientService patientService;
    private static List<MonitoredDataDTO> activities = new ArrayList<MonitoredDataDTO>();

    @Autowired
    public MonitoredDataAcquisition(MonitoredDataService monitoredDataService, PatientService patientService) {
        this.monitoredDataService = monitoredDataService;
        this.patientService = patientService;
    }

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = QUEUE)
    public void consumeMessageFromQueue(MonitoredDataDTO monitoredData){
        activities.add(monitoredData);
        //System.out.println(monitoredDataService.insert(monitoredData));
        checkRules(monitoredData);
        //System.out.println("Message: " + monitoredData);

    }


    public void checkRules(MonitoredDataDTO monitoredData){
        boolean ok = true;
        long activity_time = monitoredData.timeDif();
        String activity = monitoredData.getActivity();

        if(activity.equals("Sleeping") && activity_time > 7*1000*60*60 ){
            ok = false;
            System.out.println(activity + " bad: " + monitoredData);
        }
        if(activity.equals("Leaving") && activity_time > 5*1000*60*60){
            ok = false;
            System.out.println(activity + " bad: " + monitoredData);
        }
        if((activity.equals("Showering") || activity.equals("Grooming") || activity.equals("Toileting")) && activity_time > 1000*60*30){
            ok = false;
            System.out.println(activity + " bad: " + monitoredData);
        }

        if (!ok){
            notifyCaregiver(monitoredData);
        }

    }

    public void notifyCaregiver(MonitoredDataDTO monitoredDataDTO){
        Patient patient = patientService.findPatientByIdFull(monitoredDataDTO.getPatient_id());
        MonitoredDataDetailsDTO full = new MonitoredDataDetailsDTO(PatientBuilder.toPatientDTO(patient), CaregiverBuilder.toCaregiverDTO(patient.getCaregiver()), monitoredDataDTO.getStart(), monitoredDataDTO.getEnd(), monitoredDataDTO.getActivity());

        messagingTemplate.convertAndSend("/topic/notifications", full);
        //messagingTemplate.convertAndSendToUser("Caregiver", "/queue/notifications", monitoredDataDTO);
    }

    public static List<MonitoredDataDTO> getActivities() {
        return activities;
    }

    public static void setActivities(List<MonitoredDataDTO> activities) {
        MonitoredDataAcquisition.activities = activities;
    }


}
