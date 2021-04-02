package eu.mrndesign.matned.javaworkshop.model;

import javax.persistence.*;

@Entity
@Table(name = "country_language")
public class CountryLanguage {

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    @Id
    private String language;

    private boolean is_official;
    private Double percentage;

    public Country getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isIs_official() {
        return is_official;
    }

    public Double getPercentage() {
        return percentage;
    }


}
