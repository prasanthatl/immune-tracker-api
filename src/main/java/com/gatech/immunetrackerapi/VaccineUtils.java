package com.gatech.immunetrackerapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatech.immunetrackerapi.model.VaccineDetails;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccineUtils {

    public static String getVaccineData(){
        String jsonString = "";
        try {
            InputStream inputStream = VaccineUtils.class.getResourceAsStream("/static/VaccineData.json");
            jsonString = IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static List<VaccineDetails> getRecVaccines(){
        ObjectMapper mapper = new ObjectMapper();
        List<VaccineDetails> vaccList = new ArrayList<>();
        try {
            vaccList = Arrays.asList(mapper.readValue(getVaccineData(), VaccineDetails[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return vaccList;
    }

    public static void main(String[] a){
        System.out.println(VaccineUtils.getRecVaccines());
    }
}
