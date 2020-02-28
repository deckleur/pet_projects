package com.test.weather.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.weather.dto.Response;
import com.test.weather.entity.Weather;
import com.test.weather.repository.WeatherRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Service
public class DBService {

    @Autowired
    private WeatherRepository weatherRepository;
    @Getter @Setter
    private String city;
    @Getter @Setter
    private String weatherService;

     public Response getWeather(String city, String weatherService, int time) throws IOException, UnirestException {
         //setTimeNow
         long timeNow = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).getTime();
         //ConvertTimeToLongTime
         long userTime = time * 60 * 1000;

         //check if the city and service are in the database
        Weather weatherData = weatherRepository.findByCityAndService(city, weatherService);
        //creating response object
        Response response = new Response();
        //if the city and service are not in the database, we take from WeatherService
        if(weatherData==null) {
            WeatherService service = new WeatherService(city, weatherService);
            response = service.getWeather();
            weatherData = new Weather(city, weatherService, response.getTemp(), response.getHumidity(), LocalDateTime.now());
            weatherRepository.save(weatherData);
        }
        //if the data is in the database and more time has passed, update data from WeatherService
        else if(timeNow-Date.from(weatherData.getUpdated().atZone(ZoneId.systemDefault()).toInstant()).getTime()>userTime){
            WeatherService service = new WeatherService(city, weatherService);
            response = service.getWeather();
            weatherData.setService(weatherService);
            weatherData.setHumidity(response.getHumidity());
            weatherData.setTemperature(response.getTemp());
            weatherData.setUpdated(LocalDateTime.now());
            weatherRepository.save(weatherData);
        }
        //if the data is in the database and the time is less than requested, we take from the database as a cache
        else if(timeNow-Date.from(weatherData.getUpdated().atZone(ZoneId.systemDefault()).toInstant()).getTime()<userTime){
            response.setCity(weatherData.getCity());
            response.setService(weatherData.getService());
            response.setTemp(weatherData.getTemperature());
            response.setHumidity(weatherData.getHumidity());
        }
        return response;
    }
}
