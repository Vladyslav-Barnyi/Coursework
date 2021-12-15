package com.example.gameroom.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.gameroom.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchIgrashkaParametersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAceptSearch;

    @FXML
    private Button butBack;

    @FXML
    private TextField tfX1;

    @FXML
    private TextField tfX2;

    @FXML
    void initialize() {
        butBack.setOnAction(Event -> {
            Main.openNewScene("buyIgrashki.fxml", butBack);
        });

        butAceptSearch.setOnAction(Event -> {
            buyIgrashkiSearchController controller = new buyIgrashkiSearchController(Integer.parseInt(tfX1.getText()),
                    Integer.parseInt(tfX2.getText()));

            butAceptSearch.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("buyIgrashkiSearch.fxml"));
            fxmlLoader.setController(controller);

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setTitle("Shop");
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.setResizable(false);
            stage.show();
        });
    }

}
