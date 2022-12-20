package com.example.weatherforecast.model;

import java.io.IOException;

public class WeatherService {
    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public Weather getWeather(String cityName, String country, int day) throws IOException {
        return weatherClient.getWeather(cityName, country, day);
    }
}
