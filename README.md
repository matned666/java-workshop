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

You can launch this docker image like this :

`docker-compose up --build`


# Made by:

Mateusz N.