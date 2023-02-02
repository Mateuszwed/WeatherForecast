package com.example.weatherforecast.model.client;

import com.example.weatherforecast.model.Weather;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenWeatherMapClientTest {

    @Mock
    private JSONReader jsonReader;

    private OpenWeatherMapClient openWeatherMapClient;

    @BeforeEach
    void setUp(){
        openWeatherMapClient = new OpenWeatherMapClient(jsonReader);
    }

    @Test
    void getWeatherForecastShouldReturnForecastForFiveDays() throws Exception {
        //given
        when(jsonReader.readJsonFromUrl(anyString())).thenReturn(new JSONObject(exampleWeatherList()));
        //when
        List<Weather> weatherList = openWeatherMapClient.getWeatherForecast("city", "country");
        //then
        assertThat(weatherList, hasSize(5));
    }

    @Test
    void wrongEndpointInJSONReaderShouldThrowGetWeatherForecastException() throws Exception {
        //given
        when(jsonReader.readJsonFromUrl(anyString())).thenThrow(JSONException.class);
        //when
        //then
        assertThrows(GetWeatherForecastException.class, () -> openWeatherMapClient.getWeatherForecast("city", "country"));
    }

    @Test
    void changedStructureInJsonShouldThrowGetWeatherForecastException() throws Exception {
        //given
        when(jsonReader.readJsonFromUrl((anyString()))).thenReturn(new JSONObject(changedStructure()));
        //when
        //then
        assertThrows(GetWeatherForecastException.class, () -> openWeatherMapClient.getWeatherForecast("city", "country"));
    }

    @Test
    void emptyStructureInJsonShouldThrowGetWeatherForecastException() throws Exception{
        //given
        when(jsonReader.readJsonFromUrl(anyString())).thenReturn(new JSONObject(emptyWeatherForecast()));
        //when
        //then
        assertThrows(GetWeatherForecastException.class, () -> openWeatherMapClient.getWeatherForecast("city", "country"));
    }


    private String exampleWeatherList() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/example.json")));
    }

    private String changedStructure() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/changedStructure.json")));
    }

    private String emptyWeatherForecast() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/empty.json")));
    }

}