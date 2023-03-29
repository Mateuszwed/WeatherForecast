package com.example.weatherforecast.view;

import com.example.weatherforecast.Launcher;
import com.example.weatherforecast.controller.BaseController;
import com.example.weatherforecast.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewFactory {
    private List<Stage> activesStages;

    public ViewFactory() {
        activesStages = new ArrayList<>();
    }

    public void showMainWindow(){

        var controller = new MainWindowController("MainWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController){
        var fxmlLoader = new FXMLLoader(Launcher.class.getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        var scene = new Scene(parent);
        scene.getStylesheets().add(Launcher.class.getResource("styles.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image(String.valueOf(Launcher.class.getResource("02d.png"))));
        activesStages.add(stage);
    }

}

