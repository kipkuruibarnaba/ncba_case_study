package com.ncbacasestudy.ncba.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "Language"
)
public class LanguageInfo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name="languageCode",nullable = false)
    private String languageCode;
    @Column(name="languageName",nullable = false)
    private String languageName;
}
