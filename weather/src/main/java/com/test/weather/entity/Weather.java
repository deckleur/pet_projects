package com.test.weather.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Integer id;

    @Column(name = "city")
    @Getter @Setter private String city;

    @Column(name = "service")
    @Getter @Setter private String service;

    @Column(name = "temperature")
    @Getter @Setter private int temperature;

    @Column(name = "humidity")
    @Getter @Setter private int humidity;

    @Column(name = "updated")
    @Getter @Setter private LocalDateTime updated;

    public Weather(){}

    public Weather(String city, String service, int temperature, int humidity, LocalDateTime time) {
        this.city = city;
        this.service = service;
        this.temperature = temperature;
        this.humidity = humidity;
        this.updated = time;
    }
}