package com.example.hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private javafx.scene.control.Button next;

    @FXML
    private javafx.scene.control.Button previous;

    @FXML
    public void onNextClick()  throws IOException {
        try {
            if (Application.currWindowPath.equals("PickForMe") || Application.currWindowPath.equals("MyWord")) {
                Application.currWindowPath = "Gameplay";
            } else {
                Application.currWindowPath = "MyWord";
            }
            Application.switchWindows((Stage) next.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onPreviousClick()  throws IOException {
        try {
            if (Application.currWindowPath.equals("PickForMe") || Application.currWindowPath.equals("MyWord")) {
                Application.currWindowPath = "Menu";
            } else {
                Application.currWindowPath = "MyWord";
            }
            Application.switchWindows((Stage) previous.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}