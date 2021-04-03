# Java workshop

Application required to download image database https://github.com/ghusta/docker-postgres-world-db during build and connect to it during runtime

It loads a postgresql db from docker project

# Usage

When app is on:

GET: localhost:8080/<country_code><br>

returns json:<br>
{
"name": "xx"
"continent": "xx"
"population":xx
"life_expectancy":xx
"country_language":"xx"
}

You can use some parameters:<br>
integer countryPage  - default 0 - country start page <br>
integer countriesAmount - default 5 - number of countries shown in case there is more with the same code <br>
integer languagePage - default 0 - language start page <br>
integer languagesAmount - default 1 - number of languages shown for each country <br>
boolean isOfficial - default true - if true than shows only one official language. <br>

#Tests

Mockito, JUnit 5

H2 Datasource for testing profile

MockMVC

cityByCode - controller status ok test

service tests: <br>
findByCountryCode<br>
ifNoConnectionWithDatabaseThenReturnErrorMessage<br>
ifNonExistentCodeIsCalledThenReturnErrorMessage<br>

# Docker

You can launch this docker image from src/main/docker folder like this :

`docker-compose up --build`

# Git Versions

master branch provides version where country has one main language
listOfLanguagesPerCountry provides full list of languages used in country

# Made by:

Mateusz N.