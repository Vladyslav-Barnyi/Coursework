package com.example.gameroom.controller;

import java.io.*;
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
import javafx.scene.text.Text;

public class buyIgrashkiController implements Initializable {


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
    private Button buyIgr;

    @FXML
    private Button deleteIgr;

    @FXML
    private Button searchSumma;

    @FXML
    private Button bBack;

    @FXML
    private Button searchIgrashka;

    @FXML
    private Text text1;

    @FXML
    private Text text2;


    ObservableList<Igrashki> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        text1.setText("Залишок: " + getRemainder());

        Driver db = new Driver();
        ResultSet result = db.vievIgrashki();

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

        buyIgr.setOnAction(Event -> {
            Main.openNewScene("buy.fxml", buyIgr);
        });

        deleteIgr.setOnAction(Event -> {
            Integer price = table.getSelectionModel().getSelectedItem().getPrice() + Integer.parseInt(getRemainder());
            try {
                FileWriter writer = new FileWriter("D:\\\\balance.txt", false);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(price.toString());
                bufferWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }

            int i = table.getSelectionModel().getSelectedItem().getId();

            db.deleteIgrashka(i);
            Main.openNewScene("buyIgrashki.fxml", deleteIgr);
        });


        searchSumma.setOnAction(Event -> {
            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getPrice();
            }

            text2.setText("Cума всіх іграшок: " + sum.toString());
        });

        bBack.setOnAction(Event -> {
            Main.openNewScene("MainScene.fxml", bBack);
        });

        searchIgrashka.setOnAction(Event -> {
            Main.openNewScene("SearchIgrashkaParameters.fxml", searchIgrashka);
        });

    }

    private String getRemainder() {
        try {
            FileInputStream fstream = new FileInputStream("D:\\\\balance.txt");
            BufferedReader infile = new BufferedReader(new InputStreamReader(
                    fstream));
            String data = new String();
            if ((data = infile.readLine()) != null) {
                return data;
            }
        } catch (IOException e) {
            // Error
        }
        return "";
    }
}

