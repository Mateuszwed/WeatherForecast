package com.example.weatherforecast.client;

import com.example.weatherforecast.exception.GetWeatherForecastException;
import com.example.weatherforecast.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class OpenWeatherMapClient implements WeatherClient {
    private static final String CODE = "542ffd081e67f4512b705f89d2a611b2";
    private static final String URL_OPEN_WEATHER_MAP = "https://api.openweathermap.org/data/2.5/forecast/daily?q=";
    private static final int DAYS = 5;
    private final List<Weather> forecastWeatherList = new ArrayList<>();
    private final JSONReader jsonReader;

    public OpenWeatherMapClient(JSONReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    private String parameterRounding(String parameter) {
        var doubleParameter = (int) Math.round(Double.parseDouble(parameter));
        return String.valueOf(doubleParameter);
    }

    public List<Weather> getWeatherForecast(String cityName, String country) throws GetWeatherForecastException {
        try {
            var jsonObject = jsonReader.readJsonFromUrl(URL_OPEN_WEATHER_MAP + cityName + "," + country + "&cnt=5&appid=" + CODE + "&units=metric");
            for ( int i = 0; i < DAYS; i++ ) {
                var jsonArray = jsonObject.getJSONArray("list");
                var object = jsonArray.getJSONObject(i);
                var weatherJ = object.getJSONArray("weather");
                var weatherObject = weatherJ.getJSONObject(0);
                var icon = weatherObject.getString("icon");
                var temp = object.getJSONObject("temp");
                var city = jsonObject.getJSONObject("city");
                var jsonCityName = city.get("name").toString();
                var jsonCountry = city.get("country").toString();
                var temperatureDay = parameterRounding(temp.get("day").toString());
                var temperatureNight = parameterRounding(temp.get("night").toString());
                var humidity = parameterRounding(object.get("humidity").toString());
                var pressure = object.get("pressure").toString();
                var wind = parameterRounding(object.get("speed").toString());
                var weather = new Weather(jsonCityName, jsonCountry, temperatureDay, temperatureNight, humidity, wind, pressure, icon);
                forecastWeatherList.add(weather);
            }
        } catch (Exception e) {
            throw new GetWeatherForecastException("Problem with json reader");
        }
        return forecastWeatherList;
    }
}
