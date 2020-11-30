package com.gatech.immunetrackerapi.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class  ImmunizationData { //implements Comparable<ImmunizationData> {

    private String vaccineName;
    private String vaccineStatus;
    private String vaccineCode;
    private String vaccineDate;

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineStatus() {
        return vaccineStatus;
    }

    public void setVaccineStatus(String vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }

    public String getVaccineCode() {
        return vaccineCode;
    }

    public void setVaccineCode(String vaccineCode) {
        this.vaccineCode = vaccineCode;
    }

    public String getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(String vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    @Override
    public String toString() {
        return "ImmunizationData{" +
                "vaccineName='" + vaccineName + '\'' +
                ", vaccineStatus='" + vaccineStatus + '\'' +
                ", vaccineCode='" + vaccineCode + '\'' +
                ", vaccineDate='" + vaccineDate + '\'' +
                '}';
    }

//    @Override
//    public int compareTo(ImmunizationData im) {
//        if (getVaccineDate() == null || im.getVaccineDate() == null) {
//            return 0;
//        } else {
//            try {
//                Date currObjDate = new SimpleDateFormat("mmmddyyyy hh:mm:ss").parse(getVaccineDate());
//                System.out.println(currObjDate);
//                Date otherObjDate = new SimpleDateFormat("mmmddyyyy hh:mm:ss").parse(im.getVaccineDate());
//                System.out.println(otherObjDate);
//                return currObjDate.compareTo(otherObjDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return 0;
//
//    }
}
