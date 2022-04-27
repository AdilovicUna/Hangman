package com.example.hangman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    protected static String currWindowPath = "Menu";

    @Override
    public void start(Stage stage) throws IOException {
        startWindow(stage);
    }

    protected static void switchWindows(Stage stage)  throws IOException {
        startWindow(stage);
    }

    private static void startWindow(Stage stage)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(String.format("%s/%s.fxml", currWindowPath, currWindowPath)));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hangman");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}