package eu.mrndesign.matned.javaworkshop.repository;

import eu.mrndesign.matned.javaworkshop.model.CountryLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, String> {

    @Query("select cl from CountryLanguage cl where cl.country.countryCode = :country_code")
    Optional<CountryLanguage> findByCountryCode(@Param("country_code") String code);

}
