package com.example.laba4test;
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        HelloApplication app = new HelloApplication();

        if (app.open()) {
            app.select();
        }
        launch();
    }

    public Connection con;
    // Statement statement = con.createStatement();

    public boolean open() {
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\DB\\stuff.db");
            System.out.println("Connected!!");
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public void select() {
        try {
            Statement statement = con.createStatement();
            String query = "SELECT id, name, phone FROM stuff ORDER BY id";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                //System.out.println(id + "\t" + name + "\t" + phone);
            }
        }
        catch (Exception ex) {

        }

    }

    public void close() throws SQLException {
        con.close();
    }
}