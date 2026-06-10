package com.ncbacasestudy.ncba.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name="country_name",nullable = false)
    private String country_name;
    @Column(name="country_iso",nullable = false)
    private String country_city;
    @Column(name="country_phone",nullable = false)
    private String country_phone;
    @Column(name="country_currency",nullable = false)
    private String country_currency;
    @Column(name="country_continent",nullable = false)
    private String country_continent;
}