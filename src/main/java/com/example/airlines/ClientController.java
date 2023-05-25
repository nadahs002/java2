package com.example.airlines;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class ClientController {
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField adress;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField passport;
    @FXML
    private Button nextBtn;
    @FXML
    private Button backBtn;
    @FXML
    public void initialize()
    {
        nextBtn.setOnAction(actionEvent ->
        {
            insertClient();
            nextBtn.getScene().getWindow().hide();
            openNewScene("ClientBookingView.fxml");
        });

        backBtn.setOnAction(actionEvent ->
        {
            backBtn.getScene().getWindow().hide();
            openNewScene("LoginView.fxml");
        });
    }
    private void insertClient() {
        dataBase db = new dataBase();
        db.insertClient(firstname.getText(), lastname.getText(), adress.getText(), email.getText(), phone.getText(), passport.getText());
    }
    private void openNewScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window
            Stage currentStage = (Stage) nextBtn.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
