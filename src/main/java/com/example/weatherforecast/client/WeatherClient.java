package com.example.weatherforecast.client;


import com.example.weatherforecast.exception.GetWeatherForecastException;
import com.example.weatherforecast.model.Weather;

import java.util.List;

public interface WeatherClient {
    List<Weather> getWeatherForecast(String cityName, String country) throws GetWeatherForecastException;
}
