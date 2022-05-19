package com.example.hangman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application extends javafx.application.Application {
    protected static String currWindowPath = "Menu";
    protected static String currBackgroundPath = "";
    protected static String difficulty = "";
    protected static String type = "";
    protected static String show = "";
    protected static Model model;

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

    private String parseConfigLine(String line){
        return line.split(" ")[1];
    }

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

    protected static void writeConfig() {
        writeConfig(currBackgroundPath,difficulty,type,show);
    }

    protected static void switchWindows(Stage stage)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(String.format("%s/%s.fxml", currWindowPath, currWindowPath)));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hangman");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }

    public static void fileChooserDisplay(Stage stage)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select background");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        fileChooser.setInitialDirectory(new File("src/main/resources/com/example/hangman/Backgrounds"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        currBackgroundPath = selectedFile.toString();;
        writeConfig(currBackgroundPath, difficulty, type, show);
    }

    public static void main(String[] args) {
        launch();
    }
}