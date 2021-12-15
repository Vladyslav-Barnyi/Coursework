package com.example.gameroom.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.gameroom.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EnteredBalanceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button aceptBalance;

    @FXML
    private TextField balance;

    @FXML
    void initialize() {
        aceptBalance.setOnAction(Event -> {
//            Driver db = new Driver();
//            db.truncatetable();

            try {
                FileWriter writer = new FileWriter("D:\\\\balance.txt", false);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(balance.getText());
                bufferWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            Main.openNewScene("MainScene.fxml", aceptBalance);
        });
    }

}
