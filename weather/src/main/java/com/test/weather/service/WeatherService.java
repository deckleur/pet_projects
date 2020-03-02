package com.test.weather.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.weather.dto.Response;
import com.test.weather.entity.Services;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;

@Service
public class WeatherService {
    //mainMethods
    public Response getWeather(String cityName, String weatherService) throws IOException, UnirestException {
        //creating objects for response, restTemplate and objectMapper
        Response responseWeather = new Response();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        //JsonNode variables for making our response
        JsonNode responseNode;
        JsonNode resultNode;

        //check which weather service was selected and return the result (responseWeather)
        //**OPEN WEATHER MAP**
        if(weatherService.equals("open_weather_map")){
            //create ResponseEntity for  2xx status checking
            ResponseEntity<String> response = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&APPID=869236462d2fc799ceb53e267bfc0148", String.class);
            //check 2xx status and parsing weather for the response
            if(response.getStatusCode().is2xxSuccessful()) {
                //take weather from open weather map
                responseNode = objectMapper.readTree(new URL("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&APPID=869236462d2fc799ceb53e267bfc0148"));

                //get field "main" in Json tree of responseNode
                resultNode = responseNode.get("main");

                //setting temperature and humidity for response
                responseWeather.setTemp(resultNode.get("temp").intValue()-273.15);
                responseWeather.setHumidity(Integer.parseInt(objectMapper.writeValueAsString(resultNode.get("humidity"))));
            }
        }
        //**YANDEX WEATHER**
        else if(weatherService.equals("yandex_weather")){
            //create URL for getting latitude and longitude of city (this is a feature of Yandex Weather)
            URL url = new URL("https://geocode-maps.yandex.ru/1.x/?format=json&apikey=3b88954a-7be1-4fd7-95b9-2e6f845396e5&geocode=");
            //create ResponseEntity for 2xx status checking
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url+cityName, String.class);
            //check 2xx status of yandex service, parsing lat and long, and parsing weather for response
            if(responseEntity.getStatusCode().is2xxSuccessful()) {
                //this reading Tree for the correct reading of requests in cyrillic on Yandex service
                responseNode = objectMapper.readTree(responseEntity.getBody());
                //take array featureMember with weather result objects
                resultNode = responseNode.path("response").path("GeoObjectCollection").get("featureMember");

                //get field Point with coordination of city from array
                responseNode = resultNode.get(0);
                resultNode = responseNode.path("GeoObject").get("Point");

                //read field pos with latitude and longitude
                String coordination = objectMapper.writeValueAsString(resultNode.get("pos"));
                //split, because there are spaces in the answer line
                String[] arrCoordinate = coordination.split(" ");

                //parsing latitude and longitude of the city
                double latitude = Double.parseDouble(arrCoordinate[1].replace("\"", ""));
                double longitude = Double.parseDouble(arrCoordinate[0].replace("\"", ""));

                //Get weather with mashape lib (easy including apikey in header)
                HttpResponse<com.mashape.unirest.http.JsonNode> jsonResponse = Unirest.get("http://api.weather.yandex.ru/v1/forecast?lat=" + latitude + "&lon=" + longitude).header("X-Yandex-API-Key", "085bb073-2147-4e2f-afca-81cd763a60c1").asJson();
                com.mashape.unirest.http.JsonNode json = jsonResponse.getBody();

                //Convert JSONObject to JsonNode
                JsonFactory jsonFactory = objectMapper.getFactory();
                JsonParser jsonParser = jsonFactory.createParser(json.toString());

                //read response from our JsonNode
                responseNode = objectMapper.readTree(jsonParser);
                //get field fact for our weather info
                resultNode = responseNode.get("fact");

                //setting temperature and humidity for response
                responseWeather.setTemp(resultNode.get("temp").intValue());
                responseWeather.setHumidity(Integer.parseInt(objectMapper.writeValueAsString(resultNode.get("humidity"))));
            }
        }
        //**WORLD WEATHER ONLINE**
        else if(weatherService.equals("world_weather_online")){
            //create ResponseEntity for  2xx status checking
            ResponseEntity<String> response = restTemplate.getForEntity("http://api.worldweatheronline.com/premium/v1/weather.ashx?key=fd63f7ecc8fd4ee8a95160756202402&q="+cityName, String.class);
            //check 2xx status and parsing weather for the response
            if(response.getStatusCode().is2xxSuccessful()) {
                //take weather from world weather service
                responseNode = objectMapper.readTree(new URL("http://api.worldweatheronline.com/premium/v1/weather.ashx?key=fd63f7ecc8fd4ee8a95160756202402&format=json&q="+cityName));
                //take json array
                resultNode = responseNode.path("data").get("weather");

                //world weather online return some arrays, for this, do this
                JsonNode weatherArrayElement = resultNode.get(0);
                JsonNode hourlyArray = weatherArrayElement.get("hourly");
                JsonNode hourlyArrayElement = hourlyArray.get(0);

                //setting temperature and humidity for response
                responseWeather.setTemp(Integer.parseInt(objectMapper.writeValueAsString(hourlyArrayElement.get("tempC")).replace("\"","")));
                responseWeather.setHumidity(Integer.parseInt(objectMapper.writeValueAsString(hourlyArrayElement.get("humidity")).replace("\"","")));
            }
        }
        //setting cityName and weatherService for response
        responseWeather.setCity(cityName);
        responseWeather.setService(weatherService);

        return responseWeather;
    }
}
