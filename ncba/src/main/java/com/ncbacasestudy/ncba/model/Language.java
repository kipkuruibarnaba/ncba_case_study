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
public class Language {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name="language_code",nullable = false)
    private String language_code;
    @Column(name="language_name",nullable = false)
    private String language_name;
}
