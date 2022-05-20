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

/**
 * Controller class
 */
public class Controller {

    /**
     * next button from the application
     */
    @FXML
    private Button next;

    /**
     * main menu button from the application
     */
    @FXML
    private Button mainMenu;

    /**
     * background button from the application
     */
    @FXML
    private Button background;

    /**
     * background attribute of the application
     */
    @FXML
    private ImageView backgroundImage;

    /**
     *  toggle group for the word options of the game
     */
    @FXML
    private ToggleGroup word_choosing;

    /**
     * toggle group for the difficulty options of the word
     */
    @FXML
    private ToggleGroup difficulty;

    /**
     * toggle button for the easy option of the difficulty of the word
     */
    @FXML
    private ToggleButton easy;

    /**
     * toggle button for the medium option of the difficulty of the word
     */
    @FXML
    private ToggleButton medium;

    /**
     * toggle button for the hard option of the difficulty of the word
     */
    @FXML
    private ToggleButton hard;

    /**
     * toggle group for the type options of the word
     */
    @FXML
    private ToggleGroup type;

    /**
     * toggle button for the noun option of the type of the word
     */
    @FXML
    private ToggleButton noun;

    /**
     * toggle button for the verb option of the type of the word
     */
    @FXML
    private ToggleButton verb;

    /**
     * toggle button for the adjective option of the type of the word
     */
    @FXML
    private ToggleButton adjective;

    /**
     * toggle button for the random option of the type of the word
     */
    @FXML
    private ToggleButton randomT;

    /**
     * toggle group for the show options of the word
     */
    @FXML
    private ToggleGroup show;

    /**
     * toggle button for the none option of the show of the word
     */
    @FXML
    private ToggleButton none;

    /**
     * toggle button for the vowels option of the show of the word
     */
    @FXML
    private ToggleButton vowels;

    /**
     * toggle button for the random option of the show of the word
     */
    @FXML
    private ToggleButton randomS;

    /**
     * label of the chosen word in the game
     */
    @FXML
    private Label word;

    /**
     * toggle group for the keyboard property in the game
     */
    @FXML
    ToggleGroup abc;

    /**
     * text area of the shown word in the game
     */
    @FXML
    private TextArea mw_textArea;

    /**
     * start button from the application
     */
    @FXML
    private Button start;

    /**
     * anchor pane for the keyboard property in the game
     */
    @FXML
    private AnchorPane keyboard;

    /**
     * label for the title
     */
    @FXML
    private Label title;

    /**
     * part of the hang drawing in the game
     */
    @FXML
    private Polyline hang1;

    /**
     * part of the hang drawing in the game
     */
    @FXML
    private Polyline hang2;

    /**
     * part of the hang drawing in the game
     */
    @FXML
    private Line hang3;

    /**
     * head of the hanged man in the game
     */
    @FXML
    private Circle head;

    /**
     * body of the hanged man in the game
     */
    @FXML
    private Line body;

    /**
     * right arm of the hanged man in the game
     */
    @FXML
    private Line r_arm;

    /**
     * left arm of the hanged man in the game
     */
    @FXML
    private Line l_arm;

    /**
     * right leg of the hanged man in the game
     */
    @FXML
    private Line r_leg;

    /**
     * left leg of the hanged man in the game
     */
    @FXML
    private Line l_leg;

    /**
     * initializes the controller
     */
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

    /**
     * selects proper toggles based on the config file
     */
    private void setToggles()
    {
       setDifficulty();
       setType();
       setShow();
    }

    /**
     * selects proper difficulty toggle based on the config file
     */
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

    /**
     * selects proper type toggle based on the config file
     */
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

    /**
     * selects proper show toggle based on the config file
     */
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

    /**
     * calls the switchWindows method from the Application
     * @param stage stage of the current window
     */
    private void changeWindow(Stage stage) {
        try {
            Application.switchWindows(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * display the alert when one of the toggle groups does not have anything selected
     */
    private void selectSomethingAlert() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Invalid input");
        error.setHeaderText("INVALID INPUT!");
        error.setContentText("Please select at least one option from each category");
        error.showAndWait();
    }

    /**
     * display the alert when the word doesn't have a valid format
     */
    private void invalidWordAlert() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Invalid word");
        error.setHeaderText("INVALID WORD!");
        error.setContentText("Please enter a word that contains only lowercase letters of the English alphabet");
        error.showAndWait();
    }

    /**
     * check the format of the word
     * @return true if the format is correct
     */
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

    /**
     * initialize the model of the Application
     * @return true if the model was initialized correctly
     */
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

    /**
     * initialize the model of the Application
     * @return true if the model was initialized correctly
     */
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

    /**
     * calls the writeConfig method from the Application
     * @param newCurrBackgroundPath new variable for currBackgroundPaths
     * @param newDifficulty new variable for difficulty
     * @param newType new variable for type
     * @param newShow new variable for show
     */
    private void setConfig(String newCurrBackgroundPath, String newDifficulty, String newType, String newShow)
    {
        Application.writeConfig(newCurrBackgroundPath, newDifficulty, newType, newShow);
    }

    /**
     * called when the next button is clicked
     */
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

    /**
     * called when the next button is clicked
     */
    @FXML
    public void onMainMenuClick(){
        Application.currWindowPath = "Menu";
        changeWindow((Stage) mainMenu.getScene().getWindow());
    }

    /**
     * called when the background button is clicked
     */
    @FXML
    public void onBackgroundClick()
    {
        Application.fileChooserDisplay((Stage) background.getScene().getWindow());
        String url = new File(Application.currBackgroundPath).toString();
        backgroundImage.setImage(new Image(url));
    }

    /**
     * called when the start button is clicked
     */
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

    /**
     * called when one of the keyboard toggle buttons is clicked
     */
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

    /**
     * called when the one of the options toggle buttons is clicked
     */
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