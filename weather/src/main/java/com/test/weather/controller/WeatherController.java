package com.test.weather.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.weather.DTO.Response;
import com.test.weather.entity.Weather;
import com.test.weather.repository.WeatherRepository;
import com.test.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping("weather")
public class WeatherController {
    @Autowired
    private WeatherRepository weatherRepository;
    @GetMapping
    public Response getWeather(@RequestParam(name = "city") String city, @RequestParam(name="weatherService") String weatherService, @RequestParam(name="time") int time) throws IOException, UnirestException {
        Weather weatherS;
        Response response = new Response();
        //read from DB
        weatherS = weatherRepository.findByCityAndService(city, weatherService);
        //setTimeNow
        long timeNow = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).getTime();

        //ConvertTimeToLongTime
        long userTime = time * 60 * 1000;

        if(weatherS==null) {
            WeatherService weather = new WeatherService(city, weatherService);
            response = weather.getWeather();
            weatherS = new Weather(city, weatherService, response.getTemp(), response.getHumidity(), LocalDateTime.now());
            weatherRepository.save(weatherS);
        }
        else if(timeNow-Date.from(weatherS.getTime_up().atZone(ZoneId.systemDefault()).toInstant()).getTime()>userTime){
            WeatherService weather = new WeatherService(city, weatherService);
            response = weather.getWeather();
            weatherS.setService(weatherService);
            weatherS.setHumidity(response.getHumidity());
            weatherS.setTemperature(response.getTemp());
            weatherS.setTime_up(LocalDateTime.now());
            weatherRepository.save(weatherS);
        }
        else if(timeNow-Date.from(weatherS.getTime_up().atZone(ZoneId.systemDefault()).toInstant()).getTime()<userTime){
            response.setCity(weatherS.getCity());
            response.setService(weatherS.getService());
            response.setTemp(weatherS.getTemperature());
            response.setHumidity(weatherS.getHumidity());
        }
        else {
            WeatherService weather = new WeatherService(city, weatherService);
            response = weather.getWeather();
            weatherS = new Weather(city, weatherService, response.getTemp(), response.getHumidity(), LocalDateTime.now());
            weatherRepository.save(weatherS);
        }
        return response;
    }
}
