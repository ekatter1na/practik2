package com.example.tippingwaiters;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MainOfficiant {

    @FXML
    private Button EnterButton;

    @FXML
    private TextField numberOfPep;

    @FXML
    private TextField textFieldTip;

    @FXML
    private Label userNameLabel;

    @FXML
    void click_Enter_button(ActionEvent event) throws SQLException, ClassNotFoundException {
        DataBaseHandler db = new DataBaseHandler();
        Connection connection = db.getDbConnection();
        Statement statement = connection.createStatement();
        int rows3 = statement.executeUpdate("UPDATE User SET NumberOfUsers = '" + Integer.parseInt(numberOfPep.getText()) + "' WHERE Family ='" + userNameLabel.getText().trim()+ "'");
        int rows4 = statement.executeUpdate("UPDATE User SET SumTipping = '" + Integer.parseInt(textFieldTip.getText()) + "' WHERE Family ='" + userNameLabel.getText().trim()+ "'");
        EnterButton.getScene().getWindow().hide();
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

}
