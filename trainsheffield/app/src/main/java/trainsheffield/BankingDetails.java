package trainsheffield;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import javax.swing.JOptionPane;

public class BankingDetails {
    //verify if the bank detaiks are correct
    public static boolean verifyBank(String card, String expiry, String cvv) {
        if (verifyCardType(card) == true) {
            if (card.length() == 16 && expiry.length() == 5 && cvv.length() == 3) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Your card couldn't be verified. Please check you details and try again.", "ERROR", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Your card couldn't be verified. Please check you details and try again.", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    //verify card type as VISA/MASTER
    public static boolean verifyCardType(String card) {
        char firstCardCharacter = card.charAt(0);
        if (firstCardCharacter == '4' || firstCardCharacter == '5') {
            return true;
        } else {
            return false;
        }
    }

    //assign card type as VISA/MASTER
    public static String getCardType(String card) {
        char firstCardCharacter = card.charAt(0);
        if (firstCardCharacter == '4') {
            return "VISA";
        } else {
            return "MASTERCARD";
        }
    }

    //save bank details to db
    public static void saveBankingDetails(String userid, String card_type, String card_name, String card_number, String expiry, String cvv) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO BankingDetails (userid, card_type, card_name, card_number, expiry, cvv) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userid);
            statement.setString(2, card_type);
            statement.setString(3, card_name);
            statement.setString(4, card_number);
            statement.setString(5, expiry);
            statement.setString(6, cvv);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get users existing banking details
    public static String [] getBankingDetails() {
        try {
            String userId = Login.currentUser[0];
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BankingDetails WHERE userid = ?");
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> cardDetailsList = new ArrayList<>();
            while (resultSet.next()) {
                String cardType = resultSet.getString("card_type");
                String cardNumber = resultSet.getString("card_number");
                String cardExp = resultSet.getString("expiry");
                String cardCVV = resultSet.getString("cvv");
                String decCardNumber = decodeFromBase64(cardNumber);
                String cardDetails = cardType + "- " + decCardNumber + " " + cardExp + " " + cardCVV;
                cardDetailsList.add(cardDetails);
            }
            String[] cardDetailsArray = cardDetailsList.toArray(new String[cardDetailsList.size()]);
            return cardDetailsArray;
        } catch (SQLException e) {
            e.printStackTrace();
            //return true; 
        }
        return null;
    }

    //code to encrypt the banking details
    //code referenced from StackOverflow
    public static String encodeToBase64(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
      }

    public static String decodeFromBase64(String encodedMessage) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedMessage);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

}
