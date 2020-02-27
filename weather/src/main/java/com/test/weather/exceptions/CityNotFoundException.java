package com.test.weather.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String s){
        System.out.println(s);
    }
}