package com.example.weatherforecast.controller;

import com.example.weatherforecast.Launcher;
import com.example.weatherforecast.model.Weather;
import com.example.weatherforecast.model.WeatherService;
import com.example.weatherforecast.model.client.WeatherClientFactory;
import com.example.weatherforecast.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    private WeatherService weatherService;
    private List<Weather> weathersList = new ArrayList<Weather>();

    @FXML
    private TextField leftCountryTextField;

    @FXML
    private TextField leftCityTextField;

    @FXML
    private Label countryAndCityLabel;

    @FXML
    private HBox leftWeatherBox;

    @FXML
    private ImageView leftIcon;

    @FXML
    private Label leftTemperatureLabelShow;

    @FXML
    private Label leftWindLabelShow;

    @FXML
    private Label leftPressureLabelShow;

    @FXML
    private Label leftHumidityLabelShow;

    @FXML
    private HBox leftExtendedForecast;

    @FXML
    private Label leftError;

    @FXML
    private TextField rightCountryTextField;

    @FXML
    private TextField rightCityTextField;

    @FXML
    private Label rightCountryAndCityLabel;

    @FXML
    private HBox rightWeatherBox;

    @FXML
    private ImageView rightIcon;

    @FXML
    private Label rightTemperatureLabelShow;

    @FXML
    private Label rightWindLabelShow;

    @FXML
    private Label rightPressureLabelShow;

    @FXML
    private Label rightHumidityLabelShow;

    @FXML
    private HBox rightExtendedForecast;

    @FXML
    private Label rightError;

    @FXML
    void showLeftWeatherButtonAction() {

        String country = leftCountryTextField.getText();
        String city = leftCityTextField.getText();
        WeatherClientFactory weatherClientFactory = new WeatherClientFactory();
        WeatherService weatherService = new WeatherService(weatherClientFactory);

        try {
            for(int i = 0; i < 5; i++) {
                Weather weather = weatherService.getWeather(city, country, i);
                weathersList.add(weather);
            }
            displayLeftWeather(weathersList.get(0));
            weathersList.clear();
        }catch (IOException e){
            cleanLeftView();
            leftError.setText("Wpisano nieprawidłowe dane");
        }

    }

    @FXML
    void showRightWeatherButtonAction() {
        String country = rightCountryTextField.getText();
        String city = rightCityTextField.getText();
        WeatherClientFactory weatherClientFactory = new WeatherClientFactory();
        WeatherService weatherService = new WeatherService(weatherClientFactory);

        try {
            for(int i = 0; i < 5; i++) {
                Weather weather = weatherService.getWeather(city, country, i);
                weathersList.add(weather);
            }
            displayRightWeather(weathersList.get(0));
            weathersList.clear();
        }catch (IOException e){
            cleanRightView();
            rightError.setText("Wpisano nieprawidłowe dane");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        leftWeatherBox.setVisible(false);
        rightWeatherBox.setVisible(false);

    }

    void displayLeftWeather(Weather weather) {
        leftError.setText("");
        countryAndCityLabel.setText(weather.getCountry() + ", " + weather.getCityName());
        leftTemperatureLabelShow.setText(weather.getTemperatureDay() + "\u00B0C");
        leftWindLabelShow.setText(weather.getWind() + " km/h");
        leftPressureLabelShow.setText(weather.getPressure() + " hPa");
        leftHumidityLabelShow.setText(weather.getHumidity() + "%");
        setLeftIcons(weather);
        leftWeatherBox.setVisible(true);
    }

    void displayRightWeather(Weather weather) {

        rightError.setText("");
        rightCountryAndCityLabel.setText(weather.getCountry() + ", " + weather.getCityName());
        rightTemperatureLabelShow.setText(weather.getTemperatureDay() + "\u00B0C");
        rightWindLabelShow.setText(weather.getWind() + " km/h");
        rightPressureLabelShow.setText(weather.getPressure() + " hPa");
        rightHumidityLabelShow.setText(weather.getHumidity() + "%");
        setRightIcons(weather);
        rightWeatherBox.setVisible(true);
    }

    void setLeftIcons(Weather weather){
        leftIcon.setImage(new Image(Launcher.class.getResource(weather.getIcon() + ".png").toString()));
    }

    void setRightIcons(Weather weather){
        rightIcon.setImage(new Image(Launcher.class.getResource(weather.getIcon() + ".png").toString()));
    }

    void cleanLeftView(){
        countryAndCityLabel.setText("");
        leftWeatherBox.setVisible(false);
    }

    void cleanRightView(){
        rightCountryAndCityLabel.setText("");
        rightWeatherBox.setVisible(false);
    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

}
