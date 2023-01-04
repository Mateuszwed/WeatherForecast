package com.example.weatherforecast.model;


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
    public String toString() {
        return  cityName + " " +
                " " + country + " " + temperatureDay +
                " " + humidity +
                " " + wind +
                " " + pressure;
    }
}
