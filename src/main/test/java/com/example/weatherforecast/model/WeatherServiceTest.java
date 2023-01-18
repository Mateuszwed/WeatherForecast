package com.example.weatherforecast.model;

import com.example.weatherforecast.model.client.WeatherClient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class WeatherServiceTest {

    @Test
    void ShouldBeReturnWeatherForecast() {
        //given
        List<Weather> weatherForecast = prepareWeatherForecast();
        WeatherClient weatherClient = mock(WeatherClient.class);
        WeatherService weatherService = new WeatherService(weatherClient);
        given(weatherClient.getWeatherForecast("Kraków", "Poland")).willReturn(weatherForecast);

        //when
        List<Weather> weatherList = weatherService.getWeatherForecast("Kraków", "Poland");

        //then
        assertThat(weatherList, hasSize(5));
        assertThat(weatherService.getWeatherForecast("Kraków", "Poland"), equalTo(weatherForecast));
        assertThat(weatherClient.getWeatherForecast("Kraków", "Poland"), equalTo(weatherForecast));

    }
    @Test
    void ShouldBeThrowFailedGetWeatherForecastExceptionWhenCannotGetWeatherForecast(){
        //given
        WeatherClient weatherClient = mock(WeatherClient.class);
        WeatherService weatherService = new WeatherService(weatherClient);
        when(weatherClient.getWeatherForecast("city","country")).thenThrow(new RuntimeException());

        //when
        //then
        assertThrows(FailedGetWeatherForecastException.class, () -> weatherService.getWeatherForecast("city", "country"));
    }


    private List<Weather> prepareWeatherForecast(){
        Weather weather1 = new Weather("Kraków", "Poland", "12", "10", "70", "15", "1005", "01d");
        Weather weather2 = new Weather("Kraków", "Poland", "18", "12", "80", "13", "1007", "02d");
        Weather weather3 = new Weather("Kraków", "Poland", "17", "11", "70", "10", "1003", "03d");
        Weather weather4 = new Weather("Kraków", "Poland", "14", "13", "80", "12", "1002", "04d");
        Weather weather5 = new Weather("Kraków", "Poland", "13", "15", "70", "11", "1001", "05d");
        return Arrays.asList(weather1, weather2, weather3, weather4, weather5);
    }
}