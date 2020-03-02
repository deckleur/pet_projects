package com.test.weather.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.weather.dto.Response;
import com.test.weather.entity.Services;
import com.test.weather.exceptions.WeatherServiceNotFoundException;
import com.test.weather.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("weather")
public class WeatherController {
    @Autowired
    private MainService mainService;
    @GetMapping
    public Response getWeather(@RequestParam(name = "city") String city, @RequestParam(name="weatherService") String weatherService, @RequestParam(name="time") int time) throws IOException, UnirestException {
        try {
            //Check if there is a weather service in enum (entity/Services)
            Services.valueOf(weatherService.toUpperCase());
            return mainService.getWeather(city, weatherService, time);
        }catch (IllegalArgumentException e){
            throw new WeatherServiceNotFoundException(e.getMessage());
        }
    }
}
