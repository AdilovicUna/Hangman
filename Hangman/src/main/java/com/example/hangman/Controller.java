package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button next;

    @FXML
    private Button mainMenu;

    @FXML
    private ToggleGroup word_choosing;

    @FXML
    private ToggleGroup difficulty;

    @FXML
    private ToggleGroup type;

    @FXML
    private ToggleGroup show;

    @FXML
    private TextArea mw_textArea;

    @FXML
    private Button start;

    @FXML
    private AnchorPane keyboard;

    @FXML
    private Label title;

    @FXML
    private Polyline hang1;

    @FXML
    private Polyline hang2;

    @FXML
    private Line hang3;

    @FXML
    private Circle head;

    @FXML
    private Line body;

    @FXML
    private Line r_arm;

    @FXML
    private Line l_arm;

    @FXML
    private Line r_leg;

    @FXML
    private Line l_leg;

    @FXML
    private Label word;

    @FXML ToggleGroup abc;

    @FXML
    public void onNextClick()  throws IOException {
        try {
            if (Application.currWindowPath.equals("MyWord")) {
                if (!correctText()) {
                    invalidWordAlert();
                    return;
                }
                if (!init_MyWord()) {
                    return;
                }
                Application.currWindowPath = "Gameplay";
            } else if (Application.currWindowPath.equals("PickForMe")) {
                if (!init_PickForMe()) {
                    return;
                }
                Application.currWindowPath = "Gameplay";
            } else {
                ToggleButton button = (ToggleButton) word_choosing.getSelectedToggle();
                if (button == null) {
                    selectSomethingAlert();
                    return;
                } else {
                    Application.currWindowPath = button.idProperty().getValue();
                }
            }
            Application.switchWindows((Stage) next.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectSomethingAlert() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Invalid input");
        error.setHeaderText("INVALID INPUT!");
        error.setContentText("Please select at least one option from each category");
        error.showAndWait();
    }

    private void invalidWordAlert() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Invalid word");
        error.setHeaderText("INVALID WORD!");
        error.setContentText("Please enter a word that contains only lowercase letters of the English alphabet");
        error.showAndWait();
    }

    private Boolean correctText(){
        if (mw_textArea.getText().length() == 0) {
            return  false;
        }

        for (Character ch: mw_textArea.getText().toCharArray()) {
            if (!Character.isAlphabetic(ch) || !Character.isLowerCase(ch)) {
                return false;
            }
        }
        return true;
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

    private Boolean init_MyWord(){
        ToggleButton showButton = (ToggleButton) show.getSelectedToggle();
        if (showButton == null) {
            selectSomethingAlert();
            return false;
        }
        String selectedShow = showButton.idProperty().getValue();
        Application.model = new Model(mw_textArea.getText(),selectedShow);
        return true;
    }

    private Boolean init_PickForMe(){
        ToggleButton difficultyButton = (ToggleButton) difficulty.getSelectedToggle();
        ToggleButton typeButton = (ToggleButton) type.getSelectedToggle();
        ToggleButton showButton = (ToggleButton) show.getSelectedToggle();
        if (difficultyButton == null || typeButton == null || showButton == null) {
            selectSomethingAlert();
            return false;
        }
        String selectedDifficulty = difficultyButton.idProperty().getValue();
        String selectedType = typeButton.idProperty().getValue();
        String selectedShow = showButton.idProperty().getValue();
        Application.model = new Model(selectedDifficulty, selectedType, selectedShow);
        return true;
    }

    @FXML
    private void onStartClicked(){
        start.visibleProperty().setValue(false);
        title.visibleProperty().setValue(true);
        keyboard.visibleProperty().setValue(true);
        hang1.visibleProperty().setValue(true);
        hang2.visibleProperty().setValue(true);
        hang3.visibleProperty().setValue(true);
        word.textProperty().setValue(Application.model.wordToShow);
        word.visibleProperty().setValue(true);

        for (Node node: keyboard.getChildren()) {
            ToggleButton button = (ToggleButton) node;
            if (Application.model.lettersShown.contains(Character.toLowerCase(button.idProperty().getValue().charAt(0)))){
                button.visibleProperty().setValue(false);
            }
        }
    }

    @FXML
    public void onABCClick() throws IOException {
        ToggleButton button = (ToggleButton) abc.getSelectedToggle();
        button.setVisible(false);
        Character letter = button.idProperty().getValue().charAt(0);
        Application.model.updateWordToShow(letter);
        word.textProperty().setValue(Application.model.wordToShow);

        if (Application.model.finished) {
            Application.currWindowPath = "Win";
            Application.switchWindows((Stage) mainMenu.getScene().getWindow());
        }

        if (!Application.model.changed){
           if (!head.visibleProperty().getValue()){
               head.setVisible(true);
           } else if (!body.visibleProperty().getValue()){
               body.setVisible(true);
           } else if (!r_arm.visibleProperty().getValue()){
               r_arm.setVisible(true);
           } else if (!l_arm.visibleProperty().getValue()){
               l_arm.setVisible(true);
           } else if (!r_leg.visibleProperty().getValue()){
               r_leg.setVisible(true);
           } else if (!l_leg.visibleProperty().getValue()){
               l_leg.setVisible(true);
           } else {
               Application.currWindowPath = "GameOver";
               Application.switchWindows((Stage) mainMenu.getScene().getWindow());
           }
        }
    }

}