package com.test.weather.repository;

import com.test.weather.entity.Weather;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, String> {
    Weather findByCityAndService (String city, String service);
}