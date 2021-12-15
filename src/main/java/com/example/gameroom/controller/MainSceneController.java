package com.example.gameroom.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.gameroom.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buyIgrashki;

    @FXML
    private Button typeIgrashok;

    @FXML
    void initialize() {
        typeIgrashok.setOnAction(Event -> {
            Main.openNewScene("TypeIgrashki.fxml", typeIgrashok);
        });
        buyIgrashki.setOnAction(Event -> {
            Main.openNewScene("buyIgrashki.fxml", buyIgrashki);
        });
    }

}
