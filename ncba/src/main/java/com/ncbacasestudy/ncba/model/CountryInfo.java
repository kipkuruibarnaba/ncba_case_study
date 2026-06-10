package com.ncbacasestudy.ncba.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(
        name = "Country"
)
public class CountryInfo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name="isoCode",nullable = false)
    private String isoCode;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="capitalCity",nullable = false)
    private String capitalCity;
    @Column(name="phoneCode",nullable = false)
    private String phoneCode;
    @Column(name="continentCode",nullable = false)
    private String continentCode;
    @Column(name="currencyCode",nullable = false)
    private String currencyCode;
    @Column(name="countryFlag",nullable = false)
    private String countryFlag;



}
