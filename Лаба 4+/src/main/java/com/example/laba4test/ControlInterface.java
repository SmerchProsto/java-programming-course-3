package com.example.laba4test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlInterface {

    Stage stage;
    Parent root;
    ConnectionDB db = new ConnectionDB();

    public void openAddEmployerWindow(FXMLLoader loader) {
        try {
            stage = new Stage();
            root = loader.load(getClass().getResource("add-function-window.fxml"));
            stage.setTitle("Добавьте Работника");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            //stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow()); by it dont work(((
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDeleteEmployerWindow(FXMLLoader loader) {
        try {
            stage = new Stage();
            root = loader.load(getClass().getResource("delete-function-window.fxml"));
            stage.setTitle("Удалите Работника");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            //stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow()); by it dont work(((
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }




}
