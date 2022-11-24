package com.example.laba4test;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private AnchorPane mainWindow;
    @FXML
    private TextField cityFormLabel;

    @FXML
    private TextField managerFormLabel;

    @FXML
    private TextField nameFormLabel;

    @FXML
    private TextField numberDepartamentFormLabel;

    @FXML
    private TextField runkFormLabel;

    @FXML
    private TextField salaryFormLabel;

    @FXML
    private Button sendFormButton;

    @FXML
    private TextField telFormLabel;

    @FXML
    void initialize() {
        ArrayList<Node> childrens = new ArrayList<>();
        ConnectionDB db = new ConnectionDB();
        sendFormButton.setOnAction(actionEvent -> {
            //childrens.addAll(mainWindow.getChildren());
            for (int i = 0; i < mainWindow.getChildren().size(); i++) {
                if (mainWindow.getChildren().get(i).getClass().getName().equalsIgnoreCase("javafx.scene.control.TextField")) {
                    childrens.add(mainWindow.getChildren().get(i));
                }
            }
            if(checkAllLabels(childrens)) {
                db.addEmployeer(childrens);
                sendFormButton.getScene().getWindow().hide();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText("Пустые элементы формы!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            }
        });
    }

    boolean checkAllLabels(ArrayList<Node> childrens) {
        boolean isLabelNotEmpty = true;
        for (int i = 0; i < childrens.size(); i++) {
            if (childrens.get(i).getClass().getName().equalsIgnoreCase("javafx.scene.control.TextField")) {
                if (((TextField) childrens.get(i)).getLength() == 0) {
                    /*((TextField) childrens.get(i)).setStyle("-fx-background-color: red");*/
                    isLabelNotEmpty = false;
                    return isLabelNotEmpty;

                }
            }
        }
        return isLabelNotEmpty;
    }

}
