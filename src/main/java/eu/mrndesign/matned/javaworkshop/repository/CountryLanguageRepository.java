package eu.mrndesign.matned.javaworkshop.repository;

import eu.mrndesign.matned.javaworkshop.model.CountryLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, String> {

    @Query("select cl from CountryLanguage cl where lower(cl.country.countryCode) = lower(:country_code)")
    Page<CountryLanguage> findByCountryCode(@Param("country_code") String code, Pageable pageable);

}
