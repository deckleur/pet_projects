package com.test.weather.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.weather.dto.Response;
import com.test.weather.entity.Weather;
import com.test.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class MainService {
    private final WeatherRepository weatherRepository;

    @Autowired
    public MainService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Response getWeather(String city, String weatherService, int time) throws IOException, UnirestException {
        //check if the city and service are in the database
        Weather weatherData = weatherRepository.findByCityAndService(city, weatherService);

        //ConvertTimeToLongTime
        long userTime = time * 60 * 1000;
        boolean isUpdate = new Date().getTime() - Date.from(weatherData.getUpdated().atZone(ZoneId.systemDefault()).toInstant()).getTime()>userTime;

        //creating response object
        Response response = new Response();
        //if the city and service are not in the database, we take from WeatherService
        if (weatherData == null) {
            WeatherService service = new WeatherService();
            response = service.getWeather(city, weatherService);
            weatherData = new Weather(city, weatherService, response.getTemp(), response.getHumidity(), LocalDateTime.now());
            weatherRepository.save(weatherData);
        }
        //if the data is in the database and more time has passed, update data from WeatherService
        else {
            if (isUpdate) {
                WeatherService service = new WeatherService();
                response = service.getWeather(city, weatherService);
                weatherData.setService(weatherService);
                weatherData.setHumidity(response.getHumidity());
                weatherData.setTemperature(response.getTemp());
                weatherData.setUpdated(LocalDateTime.now());
                weatherRepository.save(weatherData);
            } else {
                response.setCity(weatherData.getCity());
                response.setService(weatherData.getService());
                response.setTemp(weatherData.getTemperature());
                response.setHumidity(weatherData.getHumidity());
            }
        }
        return response;
    }
}
