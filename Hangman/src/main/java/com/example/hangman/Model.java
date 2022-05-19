package com.example.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Model {
    public String wordToShow;
    public ArrayList<Character> lettersShown = new ArrayList<Character>() {};
    public Boolean changed = false;
    public Boolean finished = false;
    private String word = "";
    private String[] types = {"adjective", "noun", "verb"};

    public Model(String myWord, String show) {
        word = myWord;
        pickDisplayLetters(show);
    }

    public Model(String difficulty, String type, String show) {
        if (type.contains("random")) {
            type = types[new Random().nextInt(types.length)];
        }
        word = getRandomWord(String.format("/%s/%s.txt",difficulty,type));
        pickDisplayLetters(show);
    }

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

    private Character getRandomCharacter(){
        Random random = new Random();
        int index = random.nextInt(word.length());
        return word.charAt(index);
    }

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
