package com.test.weather.DTO;

public class Response {
    private int temp; //температура по-умолчанию в Кельвинах
    private int humidity; //влажность
    private String city;
    private String service;

    public Response() {
    }
    //getters используются спрингом, иначе ошибка 500 - value type
    public int getTemp() {
        return this.temp;
    }

    public void setTemp(double temp) {
        this.temp = (int) temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
