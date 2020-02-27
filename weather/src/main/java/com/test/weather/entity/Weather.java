package com.test.weather.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer weather_id;

    @Column(name = "city")
    private String city;
    @Column(name = "service")
    private String service;
    @Column(name = "temperature")
    private int temperature;
    @Column(name = "humidity")
    private int humidity;
    @Column(name = "time_up")
    private LocalDateTime time_up;

    public Weather(){}

    public Weather(String city, String service, int temperature, int humidity, LocalDateTime time) {
        this.city = city;
        this.service = service;
        this.temperature = temperature;
        this.humidity = humidity;
        this.time_up = time;
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

    public void setService(String weather_service) {
        this.service = service;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getTime_up() {
        return time_up;
    }

    public void setTime_up(LocalDateTime time_up) {
        this.time_up = time_up;
    }

    public Integer getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(Integer weather_id) {
        this.weather_id = weather_id;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weather_id=" + weather_id +
                ", city='" + city + '\'' +
                ", service='" + service + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", time_up=" + time_up +
                '}';
    }
}