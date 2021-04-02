package eu.mrndesign.matned.javaworkshop.repository;

import eu.mrndesign.matned.javaworkshop.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c from Country c where c.countryCode = ?1")
    List<Country> findByCountryCode(String id);

    @Query("select case when count(c)> 0 then true else false end from Country c")
    boolean checkConnection();
}
