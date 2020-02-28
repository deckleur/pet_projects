package com.test.weather.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WeatherServiceNotFoundException extends RuntimeException {
    public WeatherServiceNotFoundException(String message){
        super(message);
    }
}
