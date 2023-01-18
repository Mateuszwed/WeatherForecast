package com.example.weatherforecast.model.client;

import com.example.weatherforecast.model.FailedGetWeatherForecastException;
import com.example.weatherforecast.model.Weather;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherMapClient implements WeatherClient {

    private static final String CODE = "542ffd081e67f4512b705f89d2a611b2";
    private final List<Weather> forecastWeatherList = new ArrayList<>();

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private String parameterRounding(String parameter) {
        int doubleParameter = (int) Math.round(Double.parseDouble(parameter));
        return String.valueOf(doubleParameter);
    }


    public List<Weather> getWeatherForecast(String cityName, String country) {
        JSONArray jsonArray;
        try{
            JSONObject jsonObject = readJsonFromUrl("https://api.openweathermap.org/data/2.5/forecast/daily?q=" + cityName + "," + country + "&cnt=5&appid=" + CODE + "&units=metric");
            for ( int i = 0; i < 5; i++ ) {
                jsonArray = jsonObject.getJSONArray("list");
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
            }catch (Exception e) {
                e.printStackTrace();
        }
        return forecastWeatherList;
    }
}
