package com.example.gameroom.controller;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import com.example.gameroom.Igrashki;
import com.example.gameroom.Main;
import com.example.gameroom.db.Driver;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class buyIgrashkiSearchController implements Initializable {
    private int x1;
    private int x2;

    public buyIgrashkiSearchController(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Igrashki, Integer> id;

    @FXML
    private TableColumn<Igrashki, String> name;

    @FXML
    private TableColumn<Igrashki, Integer> price;

    @FXML
    private TableColumn<Igrashki, Integer> age;

    @FXML
    private TableView<Igrashki> table;

    @FXML
    private Button bBack;


    ObservableList<Igrashki> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Driver db = new Driver();
        ResultSet result = db.vievSearchTax(x1, x2);

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                list.add(new Igrashki(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        id.setCellValueFactory(new PropertyValueFactory<Igrashki, Integer>("id"));
        name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        price.setCellValueFactory(new PropertyValueFactory<Igrashki, Integer>("price"));
        age.setCellValueFactory(new PropertyValueFactory<Igrashki, Integer>("age"));

        table.setItems(list);

        bBack.setOnAction(Event -> {
            Main.openNewScene("buyIgrashki.fxml", bBack);
        });
    }
}

