package com.example.weatherforecast.model.client;

import com.example.weatherforecast.model.Weather;

import java.util.List;

public interface WeatherClient {

    List<Weather> getWeatherForecast(String cityName, String country);
}
