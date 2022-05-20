package com.example.hangman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Application class
 */
public class Application extends javafx.application.Application {
    /**
     * stores the end string of the current window path
     */
    protected static String currWindowPath = "Menu";

    /**
     * stores the string of the current path to the background image
     */
    protected static String currBackgroundPath = "";

    /**
     * stores the end string of the difficulty option selected in the game
     */
    protected static String difficulty = "";

    /**
     * stores the end string of the type option selected in the game
     */
    protected static String type = "";

    /**
     * stores the end string of the show option selected in the game
     */
    protected static String show = "";

    /**
     * stores an instance of the Model class
     */
    protected static Model model;

    /**
     * stores the path to the configuration file
     */
    private final static String configPath = System.getProperty("user.dir") + "/src/main/java/com/example/hangman/config";

    @Override
    public void start(Stage stage) throws IOException {
        readConfig();
        switchWindows(stage);
    }

    @Override
    public void stop() {
        writeConfig();
    }

    /**
     * reads and stores the content of the configuration file
     */
    private void readConfig()
    {
        Path pathToFile = Paths.get(configPath);
        File file = new File(pathToFile.toString());
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            currBackgroundPath = parseConfigLine(br.readLine());
            difficulty = parseConfigLine(br.readLine());
            type = parseConfigLine(br.readLine());
            show = parseConfigLine(br.readLine());

            currBackgroundPath = currBackgroundPath.equals(".") ? "" : currBackgroundPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param line string that we want to parse
     * @return second element of the parsed line, i.e. value of the parameter
     */
    private String parseConfigLine(String line){
        return line.split(" ")[1];
    }

    /**
     * writes the parameters to the config file
     * @param newCurrBackgroundPath new variable for currBackgroundPath
     * @param newDifficulty new variable for difficulty
     * @param newType new variable for type
     * @param newShow new variable for show
     */
    protected static void writeConfig(String newCurrBackgroundPath, String newDifficulty, String newType, String newShow){
        if (newCurrBackgroundPath.equals("")){
            newCurrBackgroundPath = ".";
        }
        if(newShow.contains("random"))
        {
            newShow = "random";
        }
        if(newType.contains("random"))
        {
            newType = "random";
        }
        String s = "currBackgroundPath " + newCurrBackgroundPath + '\n';
        s += "difficulty " + newDifficulty + '\n';
        s += "type " + newType + '\n';
        s += "show " + newShow + '\n';

        try {
            FileWriter writer = new FileWriter(Paths.get(configPath).toString(), false);
            writer.write(s);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        currBackgroundPath = newCurrBackgroundPath;
        difficulty = newDifficulty;
        type = newType;
        show = newShow;
    }

    /**
     *  writes the default parameters to the config file
     */
    protected static void writeConfig() {
        writeConfig(currBackgroundPath,difficulty,type,show);
    }

    /**
     * switches between windows based on currWindowPath parameter
     * @param stage stage for which we host the scene
     * @throws IOException necessary because of the fxmlLoader
     */
    protected static void switchWindows(Stage stage)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(String.format("%s/%s.fxml", currWindowPath, currWindowPath)));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hangman");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    /**
     * opens up a file chooser from the application
     * @param stage current stage
     */
    public static void fileChooserDisplay(Stage stage)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select background");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        fileChooser.setInitialDirectory(new File("src/main/resources/com/example/hangman/Backgrounds"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        currBackgroundPath = selectedFile.toString();
        writeConfig(currBackgroundPath, difficulty, type, show);
    }

    /**
     * entry point
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}