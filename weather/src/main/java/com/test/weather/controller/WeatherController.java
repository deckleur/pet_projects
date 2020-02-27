package com.test.weather.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.weather.DTO.Response;
import com.test.weather.service.DBService;
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
    private DBService dBService;
    @GetMapping
    public Response getWeather(@RequestParam(name = "city") String city, @RequestParam(name="weatherService") String weatherService, @RequestParam(name="time") int time) throws IOException, UnirestException {
        return dBService.getWeather(city, weatherService);
    }
}
