package com.example.weatherforecast.controller;

import com.example.weatherforecast.model.Weather;
import com.example.weatherforecast.model.WeatherService;
import com.example.weatherforecast.model.client.WeatherClientFactory;
import com.example.weatherforecast.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        leftWeatherBox.setVisible(false);
        rightWeatherBox.setVisible(false);

    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

}
