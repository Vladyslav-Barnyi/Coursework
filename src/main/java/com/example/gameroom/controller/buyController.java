package com.example.gameroom.controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.gameroom.Igrashki;
import com.example.gameroom.Main;
import com.example.gameroom.db.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class buyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> listIgrashok;

    @FXML
    private Button buyAcept;

    @FXML
    private Button butBack;

    @FXML
    private Text txBalance;

    @FXML
    private Text textError;


    @FXML
    void initialize() {
        txBalance.setText("Ваш поточний баланс: " + getRemainder());

        listIgrashok.getItems().addAll("Лялька - 19грн",
                "Мячик - 25грн",
                "Кубики - 30грн",
                "Маленька машинка - 10грн",
                "Машинка на радіо управлінні - 50грн",
                "Велика машинка - 35грн");

        buyAcept.setOnAction(Event -> {
            String choose = listIgrashok.getSelectionModel().getSelectedItem().toString();

            Igrashki igr = searchIgrashka(choose);

            if (Integer.parseInt(getRemainder()) < igr.getPrice()) {
                textError.setText("Ця іграшка задорога для вас, оберіть іншу або видаліть одну зі своїх!");
            } else {
                Integer bal = Integer.parseInt(getRemainder()) - igr.getPrice();
                try {
                    FileWriter writer = new FileWriter("D:\\\\balance.txt", false);
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(bal.toString());
                    bufferWriter.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
                Driver db = new Driver();
                db.buyIgrashka(igr);
                Main.openNewScene("buyIgrashki.fxml", buyAcept);
            }
        });

        butBack.setOnAction(Event -> {
            Main.openNewScene("buyIgrashki.fxml", butBack);
        });
    }

    private Igrashki searchIgrashka(String choose) {
        int price = 0;
        int age = 0;
        String str1 = "Лялька - 19грн";
        String str2 = "Мячик - 25грн";
        String str3 = "Кубики - 30грн";
        String str4 = "Маленька машинка - 10грн";
        String str5 = "Машинка на радіо управлінні - 50грн";
        String str6 = "Велика машинка - 35грн";

        if (str1.equals(choose)) {
            price = 19;
            age = 3;
        }
        if (str2.equals(choose)) {
            price = 25;
            age = 5;
        }
        if (str3.equals(choose)) {
            price = 30;
            age = 5;
        }
        if (str4.equals(choose)) {
            price = 10;
            age = 3;
        }
        if (str5.equals(choose)) {
            price = 50;
            age = 8;
        }
        if (str6.equals(choose)) {
            price = 35;
            age = 6;
        }

        return new Igrashki(choose, price, age);
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
