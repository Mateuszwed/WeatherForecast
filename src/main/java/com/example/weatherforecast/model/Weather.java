package com.example.weatherforecast.model;

import java.time.LocalDate;

public class Weather {

    private String cityName;
    private String country;
    private String temperatureDay;
    private String temperatureNight;
    private String humidity;
    private String wind;
    private String pressure;
    private String icon;
    private LocalDate date;

    public Weather(String cityName, String country, String temperatureDay, String temperatureNight, String humidity, String wind, String pressure, String icon, LocalDate date) {
        this.cityName = cityName;
        this.country = country;
        this.temperatureDay = temperatureDay;
        this.temperatureNight = temperatureNight;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
        this.icon = icon;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public String getCityName() {
        return cityName;
    }

    public String getTemperatureDay() {
        return temperatureDay;
    }

    public String getTemperatureNight() {
        return temperatureNight;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return  cityName + " " +
                " " + country + " " + temperatureDay +
                " " + humidity +
                " " + wind +
                " " + pressure;
    }
}
