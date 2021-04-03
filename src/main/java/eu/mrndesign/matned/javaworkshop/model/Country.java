package eu.mrndesign.matned.javaworkshop.model;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country  {

    @Id
    @Column(name = "code")
    private String countryCode;

    @Column(name = "name")
    private String countryName;
    private String continent;
    private Double surface_area;
    private Integer indep_year;
    private Integer population;
    private Double life_expectancy;
    private Double gnp;
    private Double gnp_old;
    private String local_name;
    private String government_form;
    private String head_of_state;
    private Long capital;
    private String code2;

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getContinent() {
        return continent;
    }

    public Integer getPopulation() {
        return population;
    }

    public Double getLife_expectancy() {
        return life_expectancy;
    }

}


