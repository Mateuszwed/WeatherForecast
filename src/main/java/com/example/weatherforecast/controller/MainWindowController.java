package com.example.weatherforecast.controller;

import com.example.weatherforecast.Launcher;
import com.example.weatherforecast.model.Weather;
import com.example.weatherforecast.model.WeatherService;
import com.example.weatherforecast.model.client.WeatherClientFactory;
import com.example.weatherforecast.view.DayOfWeek;
import com.example.weatherforecast.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
        setExtendedForecast(leftExtendedForecast);
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
        setExtendedForecast(rightExtendedForecast);
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
        leftExtendedForecast.setVisible(false);
    }

    void cleanRightView(){
        rightCountryAndCityLabel.setText("");
        rightWeatherBox.setVisible(false);
        rightExtendedForecast.setVisible(false);
    }

    void setExtendedForecast(HBox extendedForecast){

        extendedForecast.getChildren().clear();
        extendedForecast.setVisible(true);

        for(int i = 1; i < weathersList.size(); i++){
            VBox vbox = new VBox();
            vbox.getStyleClass().add("smallWeatherBox");
            vbox.setMinWidth(100);
            vbox.setAlignment(Pos.CENTER);

            Label cityName = new Label(DayOfWeek.getDayName(weathersList.get(i).getDate().plusDays(i)));
            ImageView icon = new ImageView(new Image(Launcher.class.getResource(weathersList.get(i).getIcon() + ".png").toString(), 35, 35, false, false));
            Label temperature = new Label(weathersList.get(i).getTemperatureDay() + "\u00B0" + "/" + weathersList.get(i).getTemperatureNight() + "\u00B0");
            Label pressure = new Label(weathersList.get(i).getPressure() + " hPa");
            Label wind = new Label(weathersList.get(i).getWind() + " km/h");


            vbox.getChildren().addAll(cityName,
                    icon,
                    temperature,
                    pressure,
                    wind);

            extendedForecast.getChildren().add(vbox);
            extendedForecast.setSpacing(15);
            extendedForecast.setAlignment(Pos.CENTER);
        }

    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

}
