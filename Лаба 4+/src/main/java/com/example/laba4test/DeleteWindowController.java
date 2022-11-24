package com.example.laba4test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DeleteWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteEmployeerButton;

    @FXML
    private TextField idEmployeer;

    @FXML
    private AnchorPane mainWindow;

    @FXML
    void initialize() {
        ConnectionDB db = new ConnectionDB();
        deleteEmployeerButton.setOnAction(actionEvent -> {
            db.deleteEmployeer(Integer.parseInt(idEmployeer.getText()));
            deleteEmployeerButton.getScene().getWindow().hide();
        });

    }

}