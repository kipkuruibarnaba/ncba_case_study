package com.ncbacasestudy.ncba.dto;
public class CountryReqDto {
    private String name;

    public CountryReqDto() {
    }

    public CountryReqDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
