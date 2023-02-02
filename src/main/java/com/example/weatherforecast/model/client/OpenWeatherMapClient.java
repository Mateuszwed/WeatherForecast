package com.example.weatherforecast.model.client;

import com.example.weatherforecast.model.Weather;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
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
        int doubleParameter = (int) Math.round(Double.parseDouble(parameter));
        return String.valueOf(doubleParameter);
    }

    public List<Weather> getWeatherForecast(String cityName, String country) throws GetWeatherForecastException {
        try {
            JSONObject jsonObject = jsonReader.readJsonFromUrl(URL_OPEN_WEATHER_MAP + cityName + "," + country + "&cnt=5&appid=" + CODE + "&units=metric");
            for ( int i = 0; i < DAYS; i++ ) {
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                JSONObject object = jsonArray.getJSONObject(i);
                JSONArray weatherJ = object.getJSONArray("weather");
                JSONObject weatherObject = weatherJ.getJSONObject(0);
                String icon = weatherObject.getString("icon");
                JSONObject temp = object.getJSONObject("temp");
                JSONObject city = jsonObject.getJSONObject("city");

                String jsonCityName = city.get("name").toString();
                String jsonCountry = city.get("country").toString();
                String temperatureDay = parameterRounding(temp.get("day").toString());
                String temperatureNight = parameterRounding(temp.get("night").toString());
                String humidity = parameterRounding(object.get("humidity").toString());
                String pressure = object.get("pressure").toString();
                String wind = parameterRounding(object.get("speed").toString());

                Weather weather = new Weather(jsonCityName, jsonCountry, temperatureDay, temperatureNight, humidity, wind, pressure, icon);
                forecastWeatherList.add(weather);
            }
        } catch (Exception e) {
            throw new GetWeatherForecastException("Problem with json reader");
        }
        return forecastWeatherList;
    }
}
