package com.example.weatherforecast.model.client;

import com.example.weatherforecast.model.Weather;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;

public class WeatherClientFactory implements WeatherClient{

    private final int NUMBER_OF_DAYS = 5;
    private final String CODE = "542ffd081e67f4512b705f89d2a611b2";

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private String parameterRounding(String parameter) {
        int doubleParameter = (int) Math.round(Double.parseDouble(parameter));
        String stringParameter = String.valueOf(doubleParameter);
        return stringParameter;
    }


    public Weather getWeather(String cityName, String country, int day) throws IOException {
        JSONArray jsonArray;
        JSONObject jsonObject = readJsonFromUrl("https://api.openweathermap.org/data/2.5/forecast/daily?q="+ cityName +","+ country +"&cnt="+ NUMBER_OF_DAYS +"&appid="+ CODE +"&units=metric");
        jsonArray = jsonObject.getJSONArray("list");

        JSONObject object = jsonArray.getJSONObject(day);
        JSONArray weather = object.getJSONArray("weather");
        JSONObject weatherObject = weather.getJSONObject(0);
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

        return new Weather(jsonCityName, jsonCountry, temperatureDay, temperatureNight, humidity, wind, pressure, icon, LocalDate.now());

    }
}
