package com.example.hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private javafx.scene.control.Button next;

    @FXML
    private javafx.scene.control.Button mainMenu;

    @FXML
    private javafx.scene.control.ToggleButton pickforme;

    @FXML
    private javafx.scene.control.ToggleButton none;

    @FXML
    private javafx.scene.control.ToggleButton vowels;

    @FXML
    private javafx.scene.control.ToggleButton d_easy;

    @FXML
    private javafx.scene.control.ToggleButton d_medium;

    @FXML
    private javafx.scene.control.ToggleButton t_noun;

    @FXML
    private javafx.scene.control.ToggleButton t_verb;

    @FXML
    private javafx.scene.control.ToggleButton t_adj;

    @FXML
    private javafx.scene.control.TextArea mw_textArea;

    @FXML
    public void onNextClick()  throws IOException {
        try {
            if (Application.currWindowPath.equals("MyWord")) {
                init_MyWord();
                Application.currWindowPath = "Gameplay";
            } else if (Application.currWindowPath.equals("PickForMe")) {
                init_PickForMe();
                Application.currWindowPath = "Gameplay";
            } else {
                Application.currWindowPath = pickforme.isSelected() ? "PickForMe" : "MyWord";
            }
            Application.switchWindows((Stage) next.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onMainMenuClick()  throws IOException {
        try {
            Application.currWindowPath = "Menu";
            Application.switchWindows((Stage) mainMenu.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onABCClick() throws IOException {
        System.out.println("Click");
    }

    private void init_MyWord(){
        String selectedShow = none.isSelected() ? "none" : vowels.isSelected() ? "vowels" : "random";
        Model m = new Model(mw_textArea.getText(),selectedShow);
    }

    private void init_PickForMe(){
        String selectedDisplay = d_easy.isSelected() ? "easy" : d_medium.isSelected() ? "medium" : "hard";
        String selectedType = t_noun.isSelected() ? "noun" : t_verb.isSelected() ? "verb" : t_adj.isSelected() ? "adjective": "random";
        String selectedShow = none.isSelected() ? "none" : vowels.isSelected() ? "vowels" : "random";
        Model m = new Model(selectedDisplay, selectedType, selectedShow);
    }
}