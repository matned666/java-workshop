package eu.mrndesign.matned.javaworkshop.dto;

import eu.mrndesign.matned.javaworkshop.model.Country;
import eu.mrndesign.matned.javaworkshop.model.CountryLanguage;

import java.util.Objects;

public class CountryDisplayDTO {

    public static CountryDisplayDTO apply(Country country, CountryLanguage countryLanguage){
        if (country != null){
            return new CountryDisplayDTO.CountryDisplayDTOBuilder(country.getCountryName())
                    .country_language(countryLanguage!=null? countryLanguage.getLanguage(): null)
                    .life_expectancy(country.getLife_expectancy())
                    .population(country.getPopulation())
                    .continent(country.getContinent())
                    .build();
        }
        return null;
    }

    private String name;
    private String continent;
    private Integer population;
    private Double life_expectancy;
    private String country_language;

    private CountryDisplayDTO(CountryDisplayDTOBuilder builder){
        this.name = builder.name;
        this.continent = builder.continent;
        this.population = builder.population;
        this.life_expectancy = builder.life_expectancy;
        this.country_language = builder.country_language;
    }

    public String getName() {
        return name;
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

    public String getCountry_language() {
        return country_language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryDisplayDTO that = (CountryDisplayDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CountryDisplayDTO{" +
                "name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population +
                ", life_expectancy=" + life_expectancy +
                ", country_language='" + country_language + '\'' +
                '}';
    }

    public static class CountryDisplayDTOBuilder{

        private String name;
        private String continent;
        private Integer population;
        private Double life_expectancy;
        private String country_language;

        public CountryDisplayDTOBuilder(String name) {
            this.name = name;
        }

        public CountryDisplayDTOBuilder continent(String continent){
         this.continent = continent;
         return this;
        }

        public CountryDisplayDTOBuilder population(Integer  population){
          this.population =  population;
          return this;
        }

        public CountryDisplayDTOBuilder life_expectancy(Double life_expectancy){
         this.life_expectancy = life_expectancy;
         return this;
        }

        public CountryDisplayDTOBuilder country_language(String country_language){
           this.country_language = country_language;
           return this;
        }

        public CountryDisplayDTO build(){
            return new CountryDisplayDTO(this);
        }

    }
}
