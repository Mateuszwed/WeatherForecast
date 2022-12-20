package com.example.weatherforecast.model;

import com.example.weatherforecast.model.client.WeatherClient;
import com.example.weatherforecast.model.client.WeatherClientFactory;

public class WeatherServiceFactory {

    public static WeatherService createWeatherService(){
        return new WeatherService(createWeatherClient());
    }

    public static WeatherClient createWeatherClient(){
        return new WeatherClientFactory();
    }
}
