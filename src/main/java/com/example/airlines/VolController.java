package com.example.airlines;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.Date;

public class VolController {
    @FXML
    private TextField idflight;
    @FXML
    private DatePicker depdate;
    @FXML
    private TextField deptime;
    @FXML
    private TextField aerodep;
    @FXML
    private TextField flightdur;
    @FXML
    private TextField aeroarr;
    @FXML
    private Button nextBtn;
    @FXML
    private Button backBtn;

    @FXML
    public void initialize() {


        backBtn.setOnAction(actionEvent ->
        {
            backBtn.getScene().getWindow().hide();
            openNewScene("ClientDashboardView.fxml");
        });
        nextBtn.setOnAction(actionEvent ->
        {
            nextBtn.getScene().getWindow().hide();
            openNewScene("OurFlightsView.fxml");
        });
    }


    public void openNewScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();

            // Close the current window
            javafx.stage.Stage currentStage = (javafx.stage.Stage) nextBtn.getScene().getWindow();
            currentStage.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void insertVol() {
        dataBase db = new dataBase();
        // Convert depdate from String to Date
        Date date = java.sql.Date.valueOf(depdate.getValue());
        db.insertVol(idflight.getText(), date, deptime.getText(), aerodep.getText(), flightdur.getText(), aeroarr.getText());
    }
}


