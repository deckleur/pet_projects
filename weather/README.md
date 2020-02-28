# Weather REST API service
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Technological stack:
  - Spring Boot
  - Spring JPA
  - Maven
  - PostreSQL
  - Liquibase
  - Jackson
  - Lombok
  - Mashape Unirest

# Description:
## Test project for zaycev.net
Choosing a weather service and city, showing temperature and humidity in JSON format, as well as caching data in DB. The user indicates the caching time during the request in minutes. Depending on the time, the data is obtained either from the weather service or from the database.
