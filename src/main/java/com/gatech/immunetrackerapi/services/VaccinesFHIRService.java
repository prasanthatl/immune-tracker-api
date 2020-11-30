package com.gatech.immunetrackerapi.services;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import ca.uhn.fhir.util.BundleUtil;
import com.gatech.immunetrackerapi.model.ImmunizationData;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Immunization;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class VaccinesFHIRService {

    public static final String baseUrl ="https://r4.smarthealthit.org";

    private IGenericClient client = null;

    public VaccinesFHIRService(){
        FhirContext ctx = FhirContext.forR4();
        client = ctx.newRestfulGenericClient(baseUrl);
    }

    public String getBirthDate(String patientId){
        String bDAY = "";
        try {
            Patient patient = client.read().resource(Patient.class).withId(patientId).execute();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            bDAY = dateFormat.format(patient.getBirthDate());
            return bDAY;
        } catch (Exception exp){
            exp.printStackTrace();
        }
        return bDAY;
    }


    public List<ImmunizationData> getImmunizationData(String patientId){
        //patientId = "d070e7dd-4d25-4c52-9fb2-78d232e1cf2c";
        String searchByURL = "Immunization?patient=" + patientId ;
        Bundle bundle = client.search()
                .byUrl(searchByURL)
                .returnBundle(Bundle.class)
                .execute();
        List<Immunization> immuneList = new ArrayList<>();
        immuneList.addAll(BundleUtil.toListOfResourcesOfType(client.getFhirContext(),bundle, Immunization.class));
        while (bundle.getLink(IBaseBundle.LINK_NEXT) != null) {
            bundle = client.loadPage().next(bundle).execute();
            immuneList.addAll(BundleUtil.toListOfResourcesOfType(client.getFhirContext(), bundle, Immunization.class));
        }
        List<ImmunizationData> givenVaccineList = new ArrayList<>();

        for(Immunization immune : immuneList){
            ImmunizationData vaccineData = new ImmunizationData();
            vaccineData.setVaccineName(immune.getVaccineCode().getText());
            vaccineData.setVaccineStatus(immune.getStatus().toString());
            vaccineData.setVaccineCode(immune.getVaccineCode().getCoding().get(0).getCode());
            vaccineData.setVaccineDate(immune.getOccurrenceDateTimeType().toHumanDisplayLocalTimezone());
            givenVaccineList.add(vaccineData);
        }
//        if(givenVaccineList.size() > 0){
//            Collections.sort(givenVaccineList);
//        }
        return givenVaccineList;
    }

    public static void main(String[] args){
        VaccinesFHIRService vac = new VaccinesFHIRService();
//        List<ImmunizationData> immList = vac.getImmunizationData("d070e7dd-4d25-4c52-9fb2-78d232e1cf2c");
//        immList.stream().forEach(v->{
//            System.out.println(v);
//        });

        vac.getBirthDate("d070e7dd-4d25-4c52-9fb2-78d232e1cf2c");
    }

}
