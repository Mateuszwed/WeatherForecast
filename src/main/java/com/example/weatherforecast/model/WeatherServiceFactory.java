package com.example.weatherforecast.model;

public class WeatherServiceFactory {

    public static WeatherService createWeatherService(){
        return new WeatherService(createWeatherClient());
    }

    public static WeatherClient createWeatherClient(){
        return new WeatherClientFactory();
    }
}
