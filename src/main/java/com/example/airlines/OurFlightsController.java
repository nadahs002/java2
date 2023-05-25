package com.example.airlines;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class OurFlightsController  implements Initializable {
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
    private Button back;
    @FXML
    private Button update;

    @FXML
    private Button cancel;

    @FXML
    private Button next;

    @FXML
    private TableView<vol> table;
    @FXML
    private TableColumn<vol, Integer> idcol;
    @FXML
    private TableColumn<vol, Date> dateCol;
    @FXML
    private TableColumn<vol, String> timecol;

    @FXML
    private TableColumn<vol, String> depcol;
    @FXML
    private TableColumn<vol, String> durcol;
    @FXML
    private TableColumn<vol, String> arrcol;

    private ObservableList<vol> vols = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idcol.setCellValueFactory(new PropertyValueFactory<vol,Integer>("idflight"));
        dateCol.setCellValueFactory(new PropertyValueFactory<vol,Date>("depdate"));
        timecol.setCellValueFactory(new PropertyValueFactory<vol,String>("deptime"));
        depcol.setCellValueFactory(new PropertyValueFactory<vol,String>("aerodep"));
        durcol.setCellValueFactory(new PropertyValueFactory<vol,String>("flightdur"));
        arrcol.setCellValueFactory(new PropertyValueFactory<vol,String>("aeroarr"));
        table.setItems(vols);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleRowSelectionChange(newSelection);
            }
        });
    }

    private void handleRowSelectionChange(vol selectedVol) {
        if (selectedVol!= null) {

            idflight.setText(String.valueOf(selectedVol.getIdflight()));
            java.sql.Date depDate = (java.sql.Date) selectedVol.getDepdate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDepDate = dateFormat.format(depDate);
            depdate.setValue(LocalDate.parse(formattedDepDate));
            deptime.setText(String.valueOf(selectedVol.getDeptime()));
            aerodep.setText(String.valueOf(selectedVol.getAerodep()));
            flightdur.setText(String.valueOf(selectedVol.getFlightdur()));
            aeroarr.setText(String.valueOf(selectedVol.getAeroarr()));
        }
    }

    public void modifier() throws SQLException {
        vol selectedVol = table.getSelectionModel().getSelectedItem();

        if (selectedVol != null) {
            int code = selectedVol.getIdflight();
            String deptime = selectedVol.getDeptime();
            String duration = selectedVol.getFlightdur();
            Date date = selectedVol.getDepdate();
            String depAer = selectedVol.getAerodep();
            String depArr = selectedVol.getAeroarr();

            Connection con = new dataBase().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE vol SET depdate = ?, deptime = ?, aerodep = ?, flightdur = ?, aeroarr = ? WHERE idflight = ?");
            ps.setDate(1, new java.sql.Date(date.getTime()));  // Set depdate as java.sql.Date
            ps.setString(2, deptime);
            ps.setString(3, depAer);
            ps.setString(4, duration);
            ps.setString(5, depArr);
            ps.setInt(6, code);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Le vol est modifié !");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur s'est produite lors de la modification du vol", ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun vol sélectionné", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void consulter() {
        try {
            Connection con = new dataBase().getConnection();
            Statement transmission = con.createStatement();
            vols.clear();
            ResultSet result = transmission.executeQuery("SELECT * FROM vol");
            //idflight
            //depdate
            //deptime
            //aerodep
            //flightdur
            //aeroarr

            while (result.next()) {
                int idflight= result.getInt("idflight");
                Date depdate = result.getDate("depdate");
                String deptime	 = String.valueOf(result.getString("deptime"));
                String aerodep	 = String.valueOf(result.getString("aerodep"));
                String flightdur	 = String.valueOf(result.getString("flightdur"));
                String aeroarr	 = String.valueOf(result.getString("aeroarr"));

                vol vol= new vol(idflight,
                        depdate	,
                        deptime,
                        aerodep	,
                        flightdur
                        ,aeroarr);
                vols.add(vol);
            }
            table.setItems(vols);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
