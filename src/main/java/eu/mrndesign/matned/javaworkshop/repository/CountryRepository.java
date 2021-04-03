package eu.mrndesign.matned.javaworkshop.repository;

import eu.mrndesign.matned.javaworkshop.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c from Country c where lower(c.countryCode) = lower(:id) or lower(c.code2) = lower(:id)")
    Page<Country> findByCountryCode(@Param("id") String id, Pageable pageable);

    @Query("select case when count(c)> 0 then true else false end from Country c")
    boolean checkConnection();
}
