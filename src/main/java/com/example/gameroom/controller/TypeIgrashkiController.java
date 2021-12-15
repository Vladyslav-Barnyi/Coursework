package com.example.gameroom.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.gameroom.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TypeIgrashkiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackType;

    @FXML
    void initialize() {
        BackType.setOnAction(Event -> {
            Main.openNewScene("MainScene.fxml", BackType);
        });
    }

}
