package com.example.tippingwaiters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class AdminMenu implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> NumberColumn;

    @FXML
    private TableView<User> OfficiantList;

    @FXML
    private Button enterButton;

    @FXML
    private TableColumn<?, ?> familyColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> resaultColumn;

    @FXML
    private TableColumn<?, ?> tippingColumn;

    @FXML
    private Label endNumberPrice;

    @FXML
    private Label endPaymentPrice;

    @FXML
    private Label endTippingPrice;

    ObservableList<User> listM = FXCollections.observableArrayList();
    Integer money = 0;
    Integer numberPeople = 0 ;
    float payment = 0;
    String text = "";
    Integer rows = 0;
    Integer endPayPrice = 0;
    public void biilsSet(){ //запись в файл
        Random rand = new Random();
        Integer randin = rand.nextInt(0,1000000000);
        try(FileWriter writer = new FileWriter(randin.toString()+".txt", false))
        {
            writer.append("Имя" + "\t" + "Фамилия" + "\t" + "Количество" + "\t" + "Чаевые" + "\t" + "Сумма оплаты");
            for(int i = 0; i <= rows; i++)
                writer.append("\n" +nameColumn.getCellData(i).toString() +"\t"
                        +  familyColumn.getCellData(i).toString()+ "\t"
                        + NumberColumn.getCellData(i).toString() + "\t"
                        + tippingColumn.getCellData(i).toString() +  "\t"
                        + resaultColumn.getCellData(i).toString());

            writer.flush();
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }
    @FXML
    void clickEnterButton(ActionEvent event) throws SQLException, ClassNotFoundException { //расчет чаевых
        DataBaseHandler db = new DataBaseHandler();
        Connection con =  db.getDbConnection();
        ResultSet rs = con.createStatement().executeQuery("select * from User");

        while (rs.next()){
            System.out.println(rows);
            Statement statement = con.createStatement();
            payment = ((float)rs.getInt("NumberOfUsers")/(float)numberPeople) * money;
            int rows3 = statement.executeUpdate("UPDATE User SET TotalSumPayment = '" + payment + "' WHERE NumberOfUsers = " + rs.getInt("NumberOfUsers"));
            endPayPrice += rs.getInt("TotalSumPayment");
        }
        endNumberPrice.setText(numberPeople.toString());
        endTippingPrice.setText(money.toString());
        endPaymentPrice.setText(endPayPrice.toString());
        listReload(listM);
        biilsSet();

    }

    @FXML
    void clickDeleteButton(ActionEvent event) throws SQLException, ClassNotFoundException { //удаление сотрудника
        DataBaseHandler db = new DataBaseHandler();
        try {
            Connection con =  db.getDbConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM User WHERE Name=?");

            int rows = st.executeUpdate("DELETE from User WHERE Name='"   + text + "'" );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listReload(listM);
    }
    @FXML
    void OnSelected(MouseEvent event) { //выбор сотрудника
        try{
            int index = OfficiantList.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
            text =  familyColumn.getCellData(index).toString();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    void clickBackButton(ActionEvent event) { //выход с окна администратора
        backButton.getScene().getWindow().hide();
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
    public void listReload( ObservableList<User> listM) throws SQLException, ClassNotFoundException { //перехагруска страницы
        listM.clear();
        DataBaseHandler db = new DataBaseHandler();
        Connection con =  db.getDbConnection();

        ResultSet rs = con.createStatement().executeQuery("select * from User");
        while (rs.next()){
            money += rs.getInt("SumTipping");
            numberPeople += rs.getInt("NumberOfUsers");

            familyColumn.setCellValueFactory(new PropertyValueFactory<>("Family"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            NumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
            tippingColumn.setCellValueFactory(new PropertyValueFactory<>("sumMoney"));
            resaultColumn.setCellValueFactory(new PropertyValueFactory<>("totalSumPay"));
            listM.add(new User(rs.getString("Family"),
                    rs.getString("Name"),rs.getInt("NumberOfUsers"), rs.getInt("SumTipping"),
                    rs.getInt("TotalSumPayment")));
        }
        OfficiantList.setItems(listM);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //инициализация
        DataBaseHandler db = new DataBaseHandler();
        try {
            Connection con =  db.getDbConnection();

            ResultSet rs = con.createStatement().executeQuery("select * from User");

            while (rs.next()){
                money += rs.getInt("SumTipping");
                numberPeople += rs.getInt("NumberOfUsers");
                familyColumn.setCellValueFactory(new PropertyValueFactory<>("Family"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
                NumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
                tippingColumn.setCellValueFactory(new PropertyValueFactory<>("sumMoney"));
                resaultColumn.setCellValueFactory(new PropertyValueFactory<>("totalSumPay"));
                rows++;
                listM.add(new User(rs.getString("Family"),
                        rs.getString("Name"),rs.getInt("NumberOfUsers"), rs.getInt("SumTipping"),
                        rs.getInt("TotalSumPayment")));
            }


            OfficiantList.setItems(listM);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
