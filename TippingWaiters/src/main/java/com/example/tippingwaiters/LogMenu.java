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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogMenu {

    @FXML
    private TextField FieldOfficiant;

    @FXML
    private Button button_reg;

    @FXML
    private Button enter_button;

    @FXML
    private TextField login_table;

    Integer counter = 0;

    @FXML
    void button_enter_menu(ActionEvent event) { //авторизация, проверка на админа или пользователя
        String login = login_table.getText().trim();
        String loginPassword = FieldOfficiant.getText().trim();
        if(login_table.getText().equals("localhost") && FieldOfficiant.getText().equals("123qweasd")){
            enter_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("adminMenu.fxml"));
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
        else if (!login.equals("") && !loginPassword.equals("")) {
            loginUser(login, loginPassword);
            if (counter >= 1) {
                enter_button.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("OfficiantMenu.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Scene scene = new Scene(root, 600, 400);
                Stage stage = new Stage();
                Label label = (Label)  scene.lookup("#userNameLabel");
                label.setText(login_table.getText().trim());
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    private void loginUser (String login, String loginPassword) {//авторизация пользователя проверка на ошибку
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setFamily(login);
        user.setName(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        while (true) {
            try {
                if (!result.next()) {
                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if (counter >= 1) ;
        else {
            Shake userLoginAnim = new Shake(login_table);
            Shake userPassAnim = new Shake(FieldOfficiant);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }

    @FXML
    void reg_Action(ActionEvent event) {//переход на окно регистрации
        button_reg.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register.fxml"));
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

}