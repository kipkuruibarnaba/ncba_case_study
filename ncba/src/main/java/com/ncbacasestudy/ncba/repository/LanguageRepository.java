package com.ncbacasestudy.ncba.repository;

import com.ncbacasestudy.ncba.model.LanguageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageInfo,Long> {
}
