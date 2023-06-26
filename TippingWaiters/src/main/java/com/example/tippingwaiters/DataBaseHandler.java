package com.example.tippingwaiters;

import java.sql.*;


public class DataBaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);
        return dbConnection;
    }

    public void signUpUser(User user){
        String insert = "INSERT INTO User (Family, Name, NumberOfUsers, SumTipping,TotalSumPayment) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFamily());
            prSt.setString(2, user.getName());
            prSt.setInt(3, 0);
            prSt.setInt(4,0 );
            prSt.setInt(5,0 );
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;

        try {

            String select = "select * from User where Family =? AND Name =?";
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getFamily());
            prSt.setString(2, user.getName());
            resSet =  prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
}

