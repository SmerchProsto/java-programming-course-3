package com.example.laba4test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signInButton;

    String login = "Punk";
    String password = "bgexxz2e";

    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader();
        try {
            signInButton.setOnAction(actionEvent -> {
                if (login.equals(loginField.getText()) && password.equals(passField.getText())) {
                    signInButton.getScene().getWindow().hide();


                    loader.setLocation(DataBaseController.class.getResource("database-window.fxml"));
                    try {
                        loader.load();
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }

                    Parent root  = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                }
                else {
                    //window with error
                }
            });
        }
        catch (Exception e) {

        }
    }

}
