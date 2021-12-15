package com.example.gameroom.db;

import com.example.gameroom.Igrashki;

import java.sql.*;

public class Driver {

    public void buyIgrashka(Igrashki igr) {
        String insert = "INSERT INTO igrashki " +
                " (name, price, age)"
                + " VALUES ('" + igr.getName() + "', " + igr.getPrice() + ", " + igr.getAge() + ")";

        Connection myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Morty2020");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(insert);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet vievIgrashki() {
        ResultSet resSet = null;

        String select = "SELECT * FROM igrashki";
        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Morty2020");


            PreparedStatement prSt = myCon.prepareStatement(select);

            resSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resSet;

    }

    public void deleteIgrashka(int id) {
        String delete = "DELETE FROM igrashki WHERE id = " + id;


        Connection myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Morty2020");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(delete);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void truncatetable() {
        String truncate = "DELETE FROM igrashki";

        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Morty2020");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(truncate);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet vievSearchTax(int x1, int x2) {
        ResultSet resSet = null;

        String select = "SELECT * FROM igrashki WHERE price >? AND price <?";
        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Morty2020");

            PreparedStatement prSt = myCon.prepareStatement(select);
            prSt.setInt(1, x1);
            prSt.setInt(2, x2);

            resSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resSet;

    }


}




