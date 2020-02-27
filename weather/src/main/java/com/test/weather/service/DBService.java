package com.test.weather.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.weather.DTO.Response;
import com.test.weather.entity.Weather;
import com.test.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Service
public class DBService {
    //vars
    @Autowired
    private WeatherRepository weatherRepository;
    private String city;
    private String weatherService;
    private int time;

    //setTimeNow
    long timeNow = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).getTime();

    //ConvertTimeToLongTime
    long userTime = time * 60 * 1000;

    public Response getWeather(String city, String weatherService) throws IOException, UnirestException {
        Weather weatherData = null;
        weatherData = weatherRepository.findByCityAndService(city, weatherService);
        Response response = new Response();
        if(weatherData==null) {
            WeatherService service = new WeatherService(city, weatherService);
            response = service.getWeather();
            weatherData = new Weather(city, weatherService, response.getTemp(), response.getHumidity(), LocalDateTime.now());
            weatherRepository.save(weatherData);
        }
        else if(timeNow-Date.from(weatherData.getTime_up().atZone(ZoneId.systemDefault()).toInstant()).getTime()>userTime){
            WeatherService service = new WeatherService(city, weatherService);
            response = service.getWeather();
            weatherData.setService(weatherService);
            weatherData.setHumidity(response.getHumidity());
            weatherData.setTemperature(response.getTemp());
            weatherData.setTime_up(LocalDateTime.now());
            weatherRepository.save(weatherData);
        }
        else if(timeNow-Date.from(weatherData.getTime_up().atZone(ZoneId.systemDefault()).toInstant()).getTime()<userTime){
            response.setCity(weatherData.getCity());
            response.setService(weatherData.getService());
            response.setTemp(weatherData.getTemperature());
            response.setHumidity(weatherData.getHumidity());
        }
        else {
            WeatherService service = new WeatherService(city, weatherService);
            response = service.getWeather();
            weatherData = new Weather(city, weatherService, response.getTemp(), response.getHumidity(), LocalDateTime.now());
            weatherRepository.save(weatherData);
        }
        return response;
    }

    //getter and setters
    public WeatherRepository getWeatherRepository() {
        return weatherRepository;
    }

    public void setWeatherRepository(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeatherService() {
        return weatherService;
    }

    public void setWeatherService(String weatherService) {
        this.weatherService = weatherService;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getTimeNow() {
        return timeNow;
    }

    public void setTimeNow(long timeNow) {
        this.timeNow = timeNow;
    }

    public long getUserTime() {
        return userTime;
    }

    public void setUserTime(long userTime) {
        this.userTime = userTime;
    }
}
