package com.test.weather.dto;

import lombok.Getter;
import lombok.Setter;

public class Response {
    @Getter
    private int temp; //температура по-умолчанию в Кельвинах

    @Getter @Setter
    private int humidity; //влажность

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
