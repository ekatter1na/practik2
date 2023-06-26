package com.example.tippingwaiters;

import animations.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationClass {


    @FXML
    private Button regButton;

    @FXML
    private TextField reg_lastName_table;

    @FXML
    private TextField reg_name_table;

    @FXML
    void reg_button_enter(ActionEvent event) {
        regButton.getScene().getWindow().hide();
        regNewUser();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("meniloging.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void regNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String name = reg_name_table.getText();
        String surname = reg_lastName_table.getText();
        User user = new User(name,surname,0,0,0);
        dbHandler.signUpUser(user);
        regButton.getScene().getWindow().hide();
    }



}
