package com.example.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Model class
 */
public class Model {
    /**
     * word as it will be displayed in the game
     */
    public String wordToShow;

    /**
     * letters of the word that are reviled in the game
     */
    public ArrayList<Character> lettersShown = new ArrayList<>() {};

    /**
     * true if there were any changes made to the wordToShow
     */
    public Boolean changed = false;

    /**
     * true when word equals wordToShow
     */
    public Boolean finished = false;

    /**
     * original word
     */
    private final String word;

    /**
     * constructor of the Model class
     * @param myWord original word
     * @param show show option
     */
    public Model(String myWord, String show) {
        word = myWord;
        pickDisplayLetters(show);
    }

    /**
     * constructor of the Model class
     * @param difficulty difficulty option
     * @param type type option
     * @param show show option
     */
    public Model(String difficulty, String type, String show) {
        String[] types = {"adjective", "noun", "verb"};

        if (type.contains("random")) {
            type = types[new Random().nextInt(types.length)];
        }
        word = getRandomWord(String.format("/%s/%s.txt",difficulty,type));
        pickDisplayLetters(show);
    }

    /**
     * picks which letters will be displayed based on the show parameter
     * @param show show option
     */
    private void pickDisplayLetters(String show){
        if (show.contains("random"))
        {
            show = "random";
        }
        switch (show){
            case "none":
                break;
            case "vowels":
                lettersShown.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
                break;
            case "random":
                // show at most 30% of the word
                int showCapacity = (int)(0.3 * word.length());
                int showCount = 0;
                while (true) {
                    Character ch = getRandomCharacter();
                    if (!lettersShown.contains(ch) && showCount < showCapacity) {
                        lettersShown.add(ch);
                        showCount += word.chars().filter(letter -> letter == ch).count();
                    } else {
                        break;
                    }
                }
                break;
        }
        createWordToShow();
    }

    /**
     * gets a random character from the word
     * @return a random character from the word
     */
    private Character getRandomCharacter(){
        Random random = new Random();
        int index = random.nextInt(word.length());
        return word.charAt(index);
    }

    /**
     * creates wordToShow based on the word and lettersShown
     */
    private void createWordToShow(){
        char[] result = new char [word.length()];
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            if (lettersShown.contains(ch)) {
                result[i] = ch;
            } else {
                result[i] = '_';
            }
        }
        wordToShow = String.valueOf(result);
    }

    /**
     * reads a random word from the file in the path
     * @param path path to one of the WORD files
     * @return word
     */
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

    /**
     * reveals all instances of the character ch in the wordToShow
     * @param ch a letter to be added
     */
    protected void updateWordToShow(Character ch){
        char[] result = new char [word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (Character.isAlphabetic(wordToShow.charAt(i)) || word.charAt(i) == ch){
                result[i] = word.charAt(i);
            } else {
                result[i] = '_';
            }
        }
        String newWordToShow = String.valueOf(result);
        changed = !wordToShow.equals(newWordToShow);
        wordToShow = newWordToShow;
        finished = wordToShow.equals(word);
    }
}
