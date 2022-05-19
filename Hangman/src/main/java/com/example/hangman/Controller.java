package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    private Button next;

    @FXML
    private Button mainMenu;

    @FXML
    private Button background;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ToggleGroup word_choosing;

    @FXML
    private ToggleGroup difficulty;

    @FXML
    private ToggleButton easy;

    @FXML
    private ToggleButton medium;

    @FXML
    private ToggleButton hard;

    @FXML
    private ToggleGroup type;

    @FXML
    private ToggleButton noun;

    @FXML
    private ToggleButton verb;

    @FXML
    private ToggleButton adjective;

    @FXML
    private ToggleButton randomT;

    @FXML
    private ToggleGroup show;

    @FXML
    private ToggleButton none;

    @FXML
    private ToggleButton vowels;

    @FXML
    private ToggleButton randomS;

    @FXML
    private Label word;

    @FXML
    ToggleGroup abc;

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
    public void initialize() {

        String url = Application.currBackgroundPath;

        try
        {
            if (!url.equals("") && !url.equals(".")) {
                url = new File(Application.currBackgroundPath).toString();
                backgroundImage.setImage(new Image(url));
            }

            if(Application.currWindowPath.equals("PickForMe")){
                setToggles();
            }

            else if(Application.currWindowPath.equals("MyWord")){
                setShow();
            }

        } catch (IllegalArgumentException fe) {
            System.out.println("Config file not valid");
        }
    }

    private void setToggles()
    {
       setDifficulty();
       setType();
       setShow();
    }

    public void setDifficulty()
    {
        if (!Application.difficulty.equals(difficulty.getSelectedToggle().toString()))
        {
            switch (Application.difficulty) {
                case "easy" -> difficulty.selectToggle(easy);
                case "medium" -> difficulty.selectToggle(medium);
                case "heard" -> difficulty.selectToggle(hard);
                default -> throw new IllegalArgumentException();
            }
        }
    }

    public void setType()
    {
        if (!Application.type.equals(type.getSelectedToggle().toString()))
        {
            switch (Application.type) {
                case "noun" -> type.selectToggle(noun);
                case "verb" -> type.selectToggle(verb);
                case "adjective" -> type.selectToggle(adjective);
                case "random" -> type.selectToggle(randomT);
                default -> throw new IllegalArgumentException();
            }
        }
    }

    public void setShow()
    {
        if (!Application.show.equals(show.getSelectedToggle().toString()))
        {
            switch (Application.show) {
                case "none" -> show.selectToggle(none);
                case "vowels" -> show.selectToggle(vowels);
                case "random" -> show.selectToggle(randomS);
                default -> throw new IllegalArgumentException();
            }
        }
    }

    private void changeWindow(Stage stage) {
        try {
            Application.switchWindows(stage);
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
        String selectedDifficulty = difficultyButton.getId();
        String selectedType = typeButton.getId();
        String selectedShow = showButton.getId();
        Application.model = new Model(selectedDifficulty, selectedType, selectedShow);
        return true;
    }

    private void setConfig(String newCurrBackgroundPath, String newDifficulty, String newType, String newShow)
    {
        Application.writeConfig(newCurrBackgroundPath, newDifficulty, newType, newShow);
    }

    @FXML
    public void onNextClick(){
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
        changeWindow((Stage) next.getScene().getWindow());
    }

    @FXML
    public void onMainMenuClick(){
        Application.currWindowPath = "Menu";
        changeWindow((Stage) mainMenu.getScene().getWindow());
    }

    @FXML
    public void onBackgroundClick()
    {
        Application.fileChooserDisplay((Stage) background.getScene().getWindow());
        String url = new File(Application.currBackgroundPath).toString();
        backgroundImage.setImage(new Image(url));
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
    public void onABCClick(){
        ToggleButton button = (ToggleButton) abc.getSelectedToggle();
        button.setVisible(false);
        Character letter = button.idProperty().getValue().charAt(0);
        Application.model.updateWordToShow(letter);
        word.textProperty().setValue(Application.model.wordToShow);

        if (Application.model.finished) {
            Application.currWindowPath = "Win";
            changeWindow((Stage) mainMenu.getScene().getWindow());
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
               changeWindow((Stage) mainMenu.getScene().getWindow());
           }
        }
    }

    @FXML
    public void onOptionToggleClick()
    {
        String diff = Application.difficulty;
        if (difficulty != null)
        {
            diff = ((ToggleButton) difficulty.getSelectedToggle()).getId();
        }

        String ty = Application.difficulty;
        if (type != null)
        {
            ty = ((ToggleButton) type.getSelectedToggle()).getId();
        }

        String sh = ((ToggleButton) show.getSelectedToggle()).getId();
        setConfig(Application.currBackgroundPath, diff , ty, sh);
    }

}