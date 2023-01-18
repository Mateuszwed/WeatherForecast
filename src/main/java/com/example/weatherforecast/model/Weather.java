package com.example.weatherforecast.model;


import java.util.Objects;

public class Weather {

    private final String cityName;
    private final String country;
    private final String temperatureDay;
    private final String temperatureNight;
    private final String humidity;
    private final String wind;
    private final String pressure;
    private final String icon;

    public Weather(String cityName, String country, String temperatureDay, String temperatureNight, String humidity, String wind, String pressure, String icon) {
        this.cityName = cityName;
        this.country = country;
        this.temperatureDay = temperatureDay;
        this.temperatureNight = temperatureNight;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
        this.icon = icon;
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
    public boolean equals(Object o) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Weather weather = (Weather) o;
        return Objects.equals(cityName, weather.cityName) && Objects.equals(country, weather.country) && Objects.equals(temperatureDay, weather.temperatureDay) && Objects.equals(temperatureNight, weather.temperatureNight) && Objects.equals(humidity, weather.humidity) && Objects.equals(wind, weather.wind) && Objects.equals(pressure, weather.pressure) && Objects.equals(icon, weather.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, country, temperatureDay, temperatureNight, humidity, wind, pressure, icon);
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
