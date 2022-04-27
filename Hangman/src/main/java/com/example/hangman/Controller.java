package com.example.hangman;

import javafx.fxml.FXML;
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
    private ToggleButton pickforme;

    @FXML
    private ToggleButton none;

    @FXML
    private ToggleButton vowels;

    @FXML
    private ToggleButton d_easy;

    @FXML
    private ToggleButton d_medium;

    @FXML
    private ToggleButton t_noun;

    @FXML
    private ToggleButton t_verb;

    @FXML
    private ToggleButton t_adj;

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

    @FXML
    private ToggleButton q;

    @FXML
    private ToggleButton w;

    @FXML
    private ToggleButton e;

    @FXML
    private ToggleButton r;

    @FXML
    private ToggleButton t;

    @FXML
    private ToggleButton y;

    @FXML
    private ToggleButton u;

    @FXML
    private ToggleButton i;

    @FXML
    private ToggleButton o;

    @FXML
    private ToggleButton p;

    @FXML
    private ToggleButton a;

    @FXML
    private ToggleButton s;

    @FXML
    private ToggleButton d;

    @FXML
    private ToggleButton f;

    @FXML
    private ToggleButton g;

    @FXML
    private ToggleButton h;

    @FXML
    private ToggleButton j;

    @FXML
    private ToggleButton k;

    @FXML
    private ToggleButton l;

    @FXML
    private ToggleButton z;

    @FXML
    private ToggleButton x;

    @FXML
    private ToggleButton c;

    @FXML
    private ToggleButton v;

    @FXML
    private ToggleButton b;

    @FXML
    private ToggleButton n;

    @FXML
    private ToggleButton m;

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

    private void init_MyWord(){
        String selectedShow = none.isSelected() ? "none" : vowels.isSelected() ? "vowels" : "random";
        Application.model = new Model(mw_textArea.getText(),selectedShow);
    }

    private void init_PickForMe(){
        String selectedDisplay = d_easy.isSelected() ? "easy" : d_medium.isSelected() ? "medium" : "hard";
        String selectedType = t_noun.isSelected() ? "noun" : t_verb.isSelected() ? "verb" : t_adj.isSelected() ? "adjective": "random";
        String selectedShow = none.isSelected() ? "none" : vowels.isSelected() ? "vowels" : "random";
        Application.model = new Model(selectedDisplay, selectedType, selectedShow);
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
    }

    @FXML
    public void onABCClick(ToggleButton button) throws IOException {
        button.setVisible(false);
        Character letter = button.idProperty().getValue().charAt(0);
        Application.model.updateWordToShow(letter);
        word.textProperty().setValue(Application.model.wordToShow);
        if (Application.model.changed){
            System.out.println("changed");
        } else {
            System.out.println("not changed");
        }
    }

    @FXML
    public void onQClick() throws IOException {
        onABCClick(q);
    }

    @FXML
    public void onWClick() throws IOException {
        onABCClick(w);
    }

    @FXML
    public void onEClick() throws IOException {
        onABCClick(e);
    }

    @FXML
    public void onRClick() throws IOException {
        onABCClick(r);
    }

    @FXML
    public void onTClick() throws IOException {
        onABCClick(t);
    }

    @FXML
    public void onYClick() throws IOException {
        onABCClick(y);
    }

    @FXML
    public void onUClick() throws IOException {
        onABCClick(u);
    }

    @FXML
    public void onIClick() throws IOException {
        onABCClick(i);
    }

    @FXML
    public void onOClick() throws IOException {
        onABCClick(o);
    }

    @FXML
    public void onPClick() throws IOException {
        onABCClick(p);
    }

    @FXML
    public void onAClick() throws IOException {
        onABCClick(a);
    }

    @FXML
    public void onSClick() throws IOException {
        onABCClick(s);
    }

    @FXML
    public void onDClick() throws IOException {
        onABCClick(d);
    }

    @FXML
    public void onFClick() throws IOException {
        onABCClick(f);
    }

    @FXML
    public void onGClick() throws IOException {
        onABCClick(g);
    }

    @FXML
    public void onHClick() throws IOException {
        onABCClick(h);
    }

    @FXML
    public void onJClick() throws IOException {
        onABCClick(j);
    }

    @FXML
    public void onKClick() throws IOException {
        onABCClick(k);
    }

    @FXML
    public void onLClick() throws IOException {
        onABCClick(l);
    }

    @FXML
    public void onZClick() throws IOException {
        onABCClick(z);
    }

    @FXML
    public void onXClick() throws IOException {
        onABCClick(x);
    }

    @FXML
    public void onCClick() throws IOException {
        onABCClick(c);
    }

    @FXML
    public void onVClick() throws IOException {
        onABCClick(v);
    }

    @FXML
    public void onBClick() throws IOException {
        onABCClick(b);
    }

    @FXML
    public void onNClick() throws IOException {
        onABCClick(n);
    }

    @FXML
    public void onMClick() throws IOException {
        onABCClick(m);
    }
}