package com.example.weatherforecast.model.service;

import com.example.weatherforecast.model.Weather;
import com.example.weatherforecast.model.client.WeatherClient;
import com.example.weatherforecast.model.client.GetWeatherForecastException;

import java.util.List;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public List<Weather> getWeatherForecast(String cityName, String country) throws GetWeatherForecastException {
            return weatherClient.getWeatherForecast(cityName, country);
    }

}