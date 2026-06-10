package com.ncbacasestudy.ncba.model;

import lombok.Data;

@Data
public class FullCountryInfo {
    String countryname;

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }
}
