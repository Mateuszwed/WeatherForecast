package com.example.weatherforecast.model.client;

import com.example.weatherforecast.model.Weather;

import java.io.IOException;

public interface WeatherClient {

    Weather getWeather(String cityName, String country, int day) throws IOException;
}
