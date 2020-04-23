package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import persistence.Database;

import java.sql.*;

public class ReceptionistController {
    @FXML
    private TextField idFld;

    @FXML
    private TextField amountFld;

    @FXML
    private Button update;

    @FXML
    private Label messageBox;

    @FXML
    private void handleButton() throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();


       double balance;
       int id = Integer.parseInt(idFld.getText());
       ResultSet rs = statement.executeQuery("SELECT * FROM tblEmployee WHERE fldEmployeeId =" + id);

       if (rs.next())
       {
           balance = Double.parseDouble(rs.getString("fldCurrency"));
           double amount = Double.parseDouble(amountFld.getText());
           double totalBalance = balance + amount;

           PreparedStatement ps = connection.prepareStatement("UPDATE tblEmployee SET fldCurrency = ?");
           ps.setDouble(1, totalBalance);
           ps.execute();
           connection.close();

           messageBox.setText(" " + "Successful" + "\n" + "Your previous balance: " + balance + " Kr" + "\n"+
                   "Your new balance: "+  totalBalance + " Kr");
           messageBox.setTextFill(Color.BLACK);
       }
       else
       {
           messageBox.setText(" " + "Wrong ID" + "\n" + "Please check your ID code.");
           messageBox.setTextFill(Color.RED);
       }
    }
}
