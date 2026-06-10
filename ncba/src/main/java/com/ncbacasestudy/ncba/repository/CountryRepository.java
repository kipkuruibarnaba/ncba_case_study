package com.ncbacasestudy.ncba.repository;

import com.ncbacasestudy.ncba.model.CountryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryInfo ,Long> {
}
