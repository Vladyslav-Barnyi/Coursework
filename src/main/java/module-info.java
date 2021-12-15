module com.example.gameroom {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.gameroom to javafx.fxml;
    exports com.example.gameroom;
    exports com.example.gameroom.db;
    opens com.example.gameroom.db to javafx.fxml;
    exports com.example.gameroom.controller;
    opens com.example.gameroom.controller to javafx.fxml;
}