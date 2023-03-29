module com.example.weatherforecast {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.json;
    requires spring.web;

    opens com.example.weatherforecast to javafx.fxml;
    opens com.example.weatherforecast.controller to javafx.fxml;
    exports com.example.weatherforecast;
    opens com.example.weatherforecast.view to javafx.fxml;
    opens com.example.weatherforecast.service to javafx.fxml;
}