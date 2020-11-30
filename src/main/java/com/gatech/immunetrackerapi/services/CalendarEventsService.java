package com.gatech.immunetrackerapi.services;

import com.gatech.immunetrackerapi.VaccineUtils;
import com.gatech.immunetrackerapi.model.Events;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CalendarEventsService {

    @Autowired
    private VaccinesFHIRService vaccinesFHIRService;

    public List<Events> getAllVaccineEvents(String patientId) throws ParseException {

        String birthDay = vaccinesFHIRService.getBirthDate(patientId);
        List<Events> eventsList = new ArrayList<>();
        if(!StringUtils.isEmpty(birthDay)) {
            Date brDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDay);
            VaccineUtils.getRecVaccines().forEach(vaccineDetails -> {
                Arrays.stream(vaccineDetails.getVaccines()).forEach(vaccineName -> {
                    Events event = new Events();
                    Date startDate = DateUtils.addMonths(brDate, vaccineDetails.getMonth());
                    Date endDate = DateUtils.addDays(startDate, 1);
                    event.setStart(startDate);
                    event.setEnd(endDate);
                    event.setTitle(vaccineName);
                    if (startDate.before(new Date())) {
                        event.setColor("red");
                        event.setDescription(vaccineName + " is past due. Check if you already received it on the side navigation.");
                    } else {
                        event.setColor("lightgreen");
                        event.setDescription(vaccineName);
                    }
                    eventsList.add(event);
                });
            });
        }

        return eventsList;
    }

    public static void main(String[] args){
        CalendarEventsService cal = new CalendarEventsService();
        try {
            List<Events> eList = cal.getAllVaccineEvents("2018-06-08");
            eList.stream().forEach(e->{
                System.out.println(e);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
