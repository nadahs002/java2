package com.example.airlines;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class dataBase {
    private Connection con;



    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airlines", "root", "");
            System.out.println("Connection established");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load MySQL JDBC driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        }
        return con;
    }


    private Connection connection;
    public void insertClient(String firstname, String lastname, String adress, String email, String phone, String passport) {
        try {
            Connection connect = getConnection();  // Use the getConnection() method to get the database connection
            String query = "INSERT INTO client (firstname, lastname, adress, email, phone, passport) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setString(1, firstname);
            preparedStmt.setString(2, lastname);
            preparedStmt.setString(3, adress);
            preparedStmt.setString(4, email);
            preparedStmt.setString(5, phone);
            preparedStmt.setString(6, passport);
            preparedStmt.executeUpdate();  // Use executeUpdate() instead of execute() for INSERT statements
            connect.close();
            System.out.println("Record inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to insert record into the database.");
            e.printStackTrace();
        }
    }

    public void insertVol(String idflight, Date depdate, String deptime, String aerodep, String flightdur, String aeroarr) {
        try {
            Connection connect = getConnection();
            String query = "INSERT INTO vol (idflight, depdate, deptime, aerodep, flightdur, aeroarr) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setInt(1, Integer.parseInt(idflight));
            preparedStmt.setDate(2, new java.sql.Date(depdate.getTime())); // Convert java.util.Date to java.sql.Date
            preparedStmt.setString(3, deptime);
            preparedStmt.setString(4, aerodep);
            preparedStmt.setString(5, flightdur);
            preparedStmt.setString(6, aeroarr);
            preparedStmt.executeUpdate();
            connect.close();
            System.out.println("vol inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to insert vol into the database.");
            e.printStackTrace();
        }
    }




    /**  public Connection getConnection() {
          return connection;
      }

      public void closeConnection() {
          try {
              connection.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
      public boolean cancelBooking(String idVol) {
          try {
              String sql = "UPDATE reservations SET status = 'Canceled' WHERE idVol = ?";
              PreparedStatement statement = connection.prepareStatement(sql);
              statement.setString(1, idVol);

              int rowsAffected = statement.executeUpdate();
              if (rowsAffected > 0) {
                  return true; // Cancellation successful
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
          return false; // Cancellation failed
      }
      public boolean confirmBooking(String idRes){
          try{
              String sql = "UPDATE reservations SET status = 'Confirmed' WHERE idRes = ?";
              PreparedStatement statement = connection.prepareStatement(sql);
              statement.setString(1, idRes);
              int rowsAffected = statement.executeUpdate();
              if (rowsAffected > 0) {
                  return true; // Confirmation successful
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
          return false; // Confirmation failed
      }*/
}
