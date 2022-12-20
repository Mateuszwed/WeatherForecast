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

public class ViewFactory {

    private ArrayList<Stage> activesStages;

    public ViewFactory() {
        activesStages = new ArrayList<Stage>();
    }

    public void showMainWindow(){

        BaseController controller = new MainWindowController(this, "MainWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController){

        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(Launcher.class.getResource("styles.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image(String.valueOf(Launcher.class.getResource("02d.png"))));
        activesStages.add(stage);
    }

}

