package com.example.hangman;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Model {
    public String word;
    public String wordToShow;
    private String[] types = {"adjective", "noun", "verb"};

    public Model(String myWord, String show) {
        word = myWord;
        pickDisplayLetters(show);
        System.out.println(word + " " + wordToShow);
    }

    public Model(String difficulty, String type, String show) {
        if (type.equals("random")) {
            type = types[new Random().nextInt(types.length)];
        }
        word = getRandomWord(String.format("/%s/%s.txt",difficulty,type));
        pickDisplayLetters(show);
        System.out.println(word + " " + wordToShow);
    }

    private void pickDisplayLetters(String show){
        char[] result = new char [word.length()];
        switch (show){
            case "none":
                wordToShow = word;
                return;
            case "vowels":
                for (int i = 0; i < word.length(); i++) {
                    if (isVowel(word.charAt(i))) {
                        result[i] = word.charAt(i);
                    } else {
                        result[i] = '_';
                    }
                }
                break;
            case "random":
                for (int i = 0; i < word.length(); i++) {
                    if (Math.random() < 0.2) {
                        result[i] = word.charAt(i);
                    } else {
                        result[i] = '_';
                    }
                }
                break;
        }
        wordToShow = String.valueOf(result);
    }

    private Boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    private String getRandomWord(String path) {
        List<String> allWords;
        try {

            Path pathToFile = Paths.get(System.getProperty("user.dir") + "/src/main/java/com/example/hangman/WORDS" + path);
            allWords = Files.readAllLines(pathToFile);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Random random = new Random();
        return allWords.get(random.nextInt(allWords.size()));
    }
}
