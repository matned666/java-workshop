package eu.mrndesign.matned.javaworkshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.mrndesign.matned.javaworkshop.model.Country;
import eu.mrndesign.matned.javaworkshop.model.CountryLanguage;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CountryDisplayDTO {

    public static CountryDisplayDTO apply(Country country, List<CountryLanguage> countryLanguages){
        if (country != null){
            return new CountryDisplayDTO.CountryDisplayDTOBuilder(country.getCountryName())
                    .life_expectancy(country.getLife_expectancy())
                    .population(country.getPopulation())
                    .continent(country.getContinent())
                    .addLanguages(countryLanguages.stream()
                            .map(CountryLanguage::getLanguage)
                            .collect(Collectors.toList()))
                    .build();
        }
        return null;
    }

    private final String name;
    private final String continent;
    private final Integer population;

    @JsonProperty("life_expectancy")
    private final Double lifeExpectancy;

    @JsonProperty("country_language")
    private final List<String> countryLanguage;

    private CountryDisplayDTO(CountryDisplayDTOBuilder builder){
        this.name = builder.name;
        this.continent = builder.continent;
        this.population = builder.population;
        this.lifeExpectancy = builder.lifeExpectancy;
        this.countryLanguage = new LinkedList<>(builder.countryLanguage);
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

    public Double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public List<String> getCountryLanguage() {
        return countryLanguage;
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
                ", life_expectancy=" + lifeExpectancy +
                ", country_languages='" + countryLanguage + '\'' +
                '}';
    }

    public static class CountryDisplayDTOBuilder{

        private final String name;
        private String continent;
        private Integer population;
        private Double lifeExpectancy;
        private final List<String> countryLanguage;

        public CountryDisplayDTOBuilder(String name) {
            this.name = name;
            countryLanguage = new LinkedList<>();
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
         this.lifeExpectancy = life_expectancy;
         return this;
        }

        public CountryDisplayDTOBuilder addLanguages(List<String> languages){
            this.countryLanguage.addAll(languages);
            return this;
        }


        public CountryDisplayDTO build(){
            return new CountryDisplayDTO(this);
        }

    }
}
