package com.example.weatherforecast.controller;

import com.example.weatherforecast.Launcher;
import com.example.weatherforecast.model.Weather;
import com.example.weatherforecast.model.client.GetWeatherForecastException;
import com.example.weatherforecast.model.client.JSONReader;
import com.example.weatherforecast.model.service.WeatherService;
import com.example.weatherforecast.model.client.OpenWeatherMapClient;
import com.example.weatherforecast.view.DayOfWeek;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    private WeatherService weatherService;
    private JSONReader jsonReader;

    @FXML
    private TextField leftCountryTextField;

    @FXML
    private TextField leftCityTextField;

    @FXML
    private Label leftCountryAndCityLabel;

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
        jsonReader = new JSONReader();
        OpenWeatherMapClient weatherClientFactory = new OpenWeatherMapClient(jsonReader);
        weatherService = new WeatherService(weatherClientFactory);

        try {
            List<Weather> weathersList = weatherService.getWeatherForecast(city, country);
            displayWeatherBoxes(weathersList,
                    leftError,
                    leftCountryAndCityLabel,
                    leftTemperatureLabelShow,
                    leftWindLabelShow,
                    leftPressureLabelShow,
                    leftHumidityLabelShow,
                    leftIcon,
                    leftWeatherBox,
                    leftExtendedForecast);
        } catch (GetWeatherForecastException e) {
            cleanView(leftCountryAndCityLabel, leftWeatherBox, leftExtendedForecast);
            leftError.setText("Wystąpił błąd");
            e.printStackTrace();
        }
    }

    @FXML
    void showRightWeatherButtonAction() {
        String country = rightCountryTextField.getText();
        String city = rightCityTextField.getText();
        jsonReader = new JSONReader();
        OpenWeatherMapClient weatherClientFactory = new OpenWeatherMapClient(jsonReader);
        weatherService = new WeatherService(weatherClientFactory);
        try {
            List<Weather> weathersList = weatherService.getWeatherForecast(city, country);
            displayWeatherBoxes(weathersList,
                    rightError,
                    rightCountryAndCityLabel,
                    rightTemperatureLabelShow,
                    rightWindLabelShow,
                    rightPressureLabelShow,
                    rightHumidityLabelShow,
                    rightIcon,
                    rightWeatherBox,
                    rightExtendedForecast);
        } catch (GetWeatherForecastException e) {
            cleanView(rightCountryAndCityLabel, rightWeatherBox, rightExtendedForecast);
            rightError.setText("Wystąpił błąd");
        }
    }

    public MainWindowController(String fxmlName) {
        super(fxmlName);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leftWeatherBox.setVisible(false);
        rightWeatherBox.setVisible(false);
    }

    void displayWeatherBoxes(List<Weather> weathersList, Label error, Label countryAndCityName, Label temperature, Label wind, Label pressure, Label humidity, ImageView icon, HBox weatherBox, HBox extendedForecast) {
        error.setText("");
        countryAndCityName.setText(weathersList.get(0).getCountry() + ", " + weathersList.get(0).getCityName());
        temperature.setText(weathersList.get(0).getTemperatureDay() + "\u00B0C");
        wind.setText(weathersList.get(0).getWind() + " km/h");
        pressure.setText(weathersList.get(0).getPressure() + " hPa");
        humidity.setText(weathersList.get(0).getHumidity() + "%");
        icon.setImage(new Image(Objects.requireNonNull(Launcher.class.getResource(weathersList.get(0).getIcon() + ".png")).toString()));
        weatherBox.setVisible(true);
        setExtendedForecast(extendedForecast, weathersList);
    }

    void cleanView(Label countryAndCityLabel, HBox weatherBox, HBox extendedForecast) {
        countryAndCityLabel.setText("");
        weatherBox.setVisible(false);
        extendedForecast.setVisible(false);
    }


    void setExtendedForecast(HBox extendedForecast, List<Weather> weathersList) {
        extendedForecast.getChildren().clear();
        extendedForecast.setVisible(true);

        for ( int i = 1; i < weathersList.size(); i++ ) {
            VBox vbox = new VBox();
            vbox.getStyleClass().add("smallWeatherBox");

            Label cityName = new Label(DayOfWeek.getDayName(LocalDate.now().plusDays(i)));
            ImageView icon = new ImageView(new Image(Objects.requireNonNull(Launcher.class.getResource(weathersList.get(i).getIcon() + ".png")).toString(), 35, 35, false, false));
            Label temperature = new Label(weathersList.get(i).getTemperatureDay() + "\u00B0" + "/" + weathersList.get(i).getTemperatureNight() + "\u00B0");
            Label pressure = new Label(weathersList.get(i).getPressure() + " hPa");
            Label wind = new Label(weathersList.get(i).getWind() + " km/h");


            vbox.getChildren().addAll(cityName,
                    icon,
                    temperature,
                    pressure,
                    wind);

            extendedForecast.getChildren().add(vbox);
        }
    }
}
