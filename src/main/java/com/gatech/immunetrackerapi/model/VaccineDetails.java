package com.gatech.immunetrackerapi.model;

public class VaccineDetails {

    private Integer month;

    private String[] vaccines;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer months) {
        this.month = months;
    }

    public String[] getVaccines() {
        return vaccines;
    }

    public void setVaccines(String[] vaccines) {
        this.vaccines = vaccines;
    }
}
