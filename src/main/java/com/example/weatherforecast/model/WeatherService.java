package com.example.weatherforecast.model;

import com.example.weatherforecast.model.client.WeatherClient;

import java.io.IOException;
import java.util.List;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public List<Weather> getWeatherForecast(String cityName, String country){
        return weatherClient.getWeatherForecast(cityName, country);
    }
}
