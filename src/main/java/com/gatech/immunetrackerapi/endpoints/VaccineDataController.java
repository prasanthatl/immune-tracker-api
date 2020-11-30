package com.gatech.immunetrackerapi.endpoints;

import com.gatech.immunetrackerapi.VaccineUtils;
import com.gatech.immunetrackerapi.model.Events;
import com.gatech.immunetrackerapi.model.ImmunizationData;
import com.gatech.immunetrackerapi.model.VaccineDetails;
import com.gatech.immunetrackerapi.services.CalendarEventsService;
import com.gatech.immunetrackerapi.services.VaccinesFHIRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vaccines")
public class VaccineDataController {

    @Autowired
    private VaccinesFHIRService vaccinesFHIRService;

    @Autowired
    private CalendarEventsService calendarEventsService;

    @GetMapping("/patient/{patient_id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ImmunizationData> getAllVaccines(@PathVariable("patient_id") String patientId ){
        List<ImmunizationData> immuneList = vaccinesFHIRService.getImmunizationData(patientId);
        return immuneList;
    }

    @GetMapping("/patient/recvaccines")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<VaccineDetails> getAllRecVaccines(){
        List<VaccineDetails> vacRecList = VaccineUtils.getRecVaccines();
        System.out.println("size=="+vacRecList.size());
        return vacRecList;
    }

    @GetMapping("/calendar_events/{patient_id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Events> getAllCalendarEvents(@PathVariable("patient_id") String patientId) throws ParseException {
        return calendarEventsService.getAllVaccineEvents(patientId);
    }


}
