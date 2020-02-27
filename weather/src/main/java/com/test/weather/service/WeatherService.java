package com.test.weather.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.weather.DTO.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;

@Component
public class WeatherService {
    private String cityName = null;
    private String weatherService = null;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService(){}

    public WeatherService(String cityName, String weatherService) {
        this.cityName = cityName;
        this.weatherService = weatherService;
    }

    //getters
    public String getCityName() {
        return cityName;
    }
    public String getWeatherService(){ return weatherService; }

    //mainMethods
    public Response getWeather() throws IOException, UnirestException {
        Response responseWeather = new Response();
        //JsonNode variables
        JsonNode responseNode;
        JsonNode resultNode;
        //проверяем какой погодный сервис был выбран и возвращаем результат
        //**OPEN WEATHER MAP**
        if(weatherService.equals("openweathermap")){
            ResponseEntity<String> response = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&APPID=869236462d2fc799ceb53e267bfc0148", String.class);
            if(response.getStatusCode().is2xxSuccessful()) { //проверяем доступность ресурса - если 2хх статус, возвращаем json weather response
                responseNode = objectMapper.readTree(new URL("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&APPID=869236462d2fc799ceb53e267bfc0148"));
                resultNode = responseNode.get("main");

                responseWeather.setTemp(resultNode.get("temp").intValue()-273.15);
                responseWeather.setHumidity(Integer.parseInt(objectMapper.writeValueAsString(resultNode.get("humidity"))));
            }
        }
        //**YANDEX WEATHER**
        else if(weatherService.equals("weather_yandex")){
            //get city coordination from yandex geocoder
            RestTemplate restTemplate = new RestTemplate();
            URL url = new URL("https://geocode-maps.yandex.ru/1.x/?format=json&apikey=3b88954a-7be1-4fd7-95b9-2e6f845396e5&geocode=");

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url+cityName, String.class);
            if(responseEntity.getStatusCode().is2xxSuccessful()) {
                responseNode = objectMapper.readTree(responseEntity.getBody());
                resultNode = responseNode.path("response").path("GeoObjectCollection").get("featureMember");

                responseNode = resultNode.get(0);
                resultNode = responseNode.path("GeoObject").get("Point");

                String coordination = objectMapper.writeValueAsString(resultNode.get("pos"));
                String[] arrCoordinate = coordination.split(" ");

                double latitude = Double.parseDouble(arrCoordinate[1].replace("\"", ""));
                double longitude = Double.parseDouble(arrCoordinate[0].replace("\"", ""));

                //Get weather with mashape lib (easy including apikey in header)
                HttpResponse<com.mashape.unirest.http.JsonNode> jsonResponse = Unirest.get("http://api.weather.yandex.ru/v1/forecast?lat=" + latitude + "&lon=" + longitude).header("X-Yandex-API-Key", "085bb073-2147-4e2f-afca-81cd763a60c1").asJson();
                com.mashape.unirest.http.JsonNode json = jsonResponse.getBody();
                //Convert JSONObject to JsonNode
                JsonFactory jsonFactory = objectMapper.getFactory();
                JsonParser jsonParser = jsonFactory.createParser(json.toString());

                responseNode = objectMapper.readTree(jsonParser);
                resultNode = responseNode.get("fact");

                responseWeather.setTemp(resultNode.get("temp").intValue());
                responseWeather.setHumidity(Integer.parseInt(objectMapper.writeValueAsString(resultNode.get("humidity"))));
            }
        }
        //**WorldWeatherOnline**
        else if(weatherService.equals("worldweatheronline")){
            ResponseEntity<String> response = restTemplate.getForEntity("http://api.worldweatheronline.com/premium/v1/weather.ashx?key=fd63f7ecc8fd4ee8a95160756202402&q="+cityName, String.class);
            if(response.getStatusCode().is2xxSuccessful()) { //проверяем доступность ресурса - если 2хх статус, возвращаем json weather response
                responseNode = objectMapper.readTree(new URL("http://api.worldweatheronline.com/premium/v1/weather.ashx?key=fd63f7ecc8fd4ee8a95160756202402&format=json&q="+cityName));
                resultNode = responseNode.path("data").get("weather");

                JsonNode weatherArrayElement = resultNode.get(0);
                JsonNode hourlyArray = weatherArrayElement.get("hourly");
                JsonNode hourlyArrayElement = hourlyArray.get(0);

                responseWeather.setTemp(Integer.parseInt(objectMapper.writeValueAsString(hourlyArrayElement.get("tempC")).replace("\"","")));
                responseWeather.setHumidity(Integer.parseInt(objectMapper.writeValueAsString(hourlyArrayElement.get("humidity")).replace("\"","")));
            }
        }
        responseWeather.setCity(cityName);
        responseWeather.setService(weatherService);
        return responseWeather;
    }
}
