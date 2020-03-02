package com.test.weather.dto;

import com.test.weather.entity.Services;
import lombok.Getter;
import lombok.Setter;

public class Response {
    @Getter
    private int temp;

    @Getter @Setter
    private int humidity;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String service;

    public Response() {
    }

    //exclusive setter for temp
    public void setTemp(double temp) {
        this.temp = (int)temp;
    }
}
