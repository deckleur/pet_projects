package com.test.weather.repository;

import com.test.weather.entity.Services;
import com.test.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    Weather findByCityAndService (String city, String service);
}