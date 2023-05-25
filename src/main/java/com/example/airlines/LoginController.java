package com.example.airlines;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginBtn;
    private Connection connection;


    public void handleLoginBtn() {
        String username = this.username.getText();
        String password = this.password.getText();

        dataBase database = new dataBase();
        Connection conn = database.getConnection();

        if (conn != null) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String userType = resultSet.getString("username");

                    if (userType.equals("admin")) {
                        System.out.println("Admin login successful");
                        openAdminDashboard();
                    } else if (userType.equals("user")) {
                        System.out.println("User login successful");
                        openClientDashboard();
                    }
                } else {
                    System.out.println("Invalid username or password");
                }

                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error executing the SQL query");
                e.printStackTrace();

            }
        }
    }
    private void openAdminDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent root = loader.load();
            AdminController adminController = loader.getController();
            //adminController.setConnection(connection);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openClientDashboard(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
            Parent root = loader.load();
            ClientController clientController = loader.getController();
            //clientDashboardController.setConnection(connection);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* public void closeConnnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}

