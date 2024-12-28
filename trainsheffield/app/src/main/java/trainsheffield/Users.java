package trainsheffield;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Users {
  public enum Roles { CUSTOMER, STAFFMEMBER, MANAGER };


//Generate a random userid starting with 5 digits
public static String generateUserid(Connection connection) {
  String userId;
  do {
      userId = String.format("%05d", (int) (Math.random() * 100000));
  } while (checkUserIdExists(connection, userId));

  return userId;
}

// Check if the user id already exists in the database
private static boolean checkUserIdExists(Connection connection, String userId) {
  try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE user_id = ?");
      preparedStatement.setString(1, userId);
      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.next();
  } catch (SQLException e) {
      e.printStackTrace();
      return true;
  }
}

public static String[] getAddress(Connection connection, String userId) {
  try {
      String[] addressDet = new String[4]; // Assuming there are 4 fields: house_number, road_name, city, postcode
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Address WHERE user_address = ?");
      preparedStatement.setString(1, userId);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
          // Retrieve values from the result set and store them in the array
          addressDet[0] = resultSet.getString("house_number");
          addressDet[1] = resultSet.getString("road_name");
          addressDet[2] = resultSet.getString("city");
          addressDet[3] = resultSet.getString("postcode");
      }

      return addressDet;
  } catch (SQLException e) {
      e.printStackTrace();
      // Handle the exception or return an error value
  }
  return null;
}


public static String getUserid(Connection connection, String email) {
  try {
     String sql = "SELECT user_id FROM Users WHERE email = ?";
     PreparedStatement statement = connection.prepareStatement(sql);
     statement.setString(1, email);
     ResultSet results = statement.executeQuery();
     if (results.next()) {
        return results.getString("user_id");
     }
  } catch (SQLException e) {
     e.printStackTrace();
  }
  return null; // Return null if no password is found or an error occurs.
}

public static String getUserName(Connection connection, String user_id) {
  try {
     String sql = "SELECT * FROM Users WHERE user_id = ?";
     PreparedStatement statement = connection.prepareStatement(sql);
     statement.setString(1, user_id);
     ResultSet results = statement.executeQuery();
     if (results.next()) {
        String firstName = results.getString("first_name");
        String lastName = results.getString("last_name");
        String fullName = firstName + " " + lastName;
        return fullName;
     }
  } catch (SQLException e) {
     e.printStackTrace();
  }
  return null;
}

public static String getUserEmail(Connection connection, String user_id) {
  try {
     String sql = "SELECT * FROM Users WHERE user_id = ?";
     PreparedStatement statement = connection.prepareStatement(sql);
     statement.setString(1, user_id);
     ResultSet results = statement.executeQuery();
     if (results.next()) {
        String email = results.getString("email");
        return email;
     }
  } catch (SQLException e) {
     e.printStackTrace();
  }
  return null;
}

public static boolean checkUserExists(Connection connection, String email) {
  try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE email = ?");
      preparedStatement.setString(1, email);
      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.next();
  } catch (SQLException e) {
      e.printStackTrace();
      return true;
  }
}

//Return the users role based on the userid
  public static String getRole(String userid) {
  try {
    Connection connection = DatabaseManager.getConnection();
    String sql = "SELECT user_role FROM Users WHERE user_id = ?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, userid);
    ResultSet results = statement.executeQuery();
    if (results.next()) {
      return results.getString("user_role");
    }
  } catch (SQLException e) {
     e.printStackTrace();
  }
  return null;
}

  //logout user
  public static void logout() {
    JOptionPane.showMessageDialog(null, "You are logging out. Note: Products added in your cart will be deleted.", "ERROR", JOptionPane.WARNING_MESSAGE);
    System.exit(0);
  }

  //Password Hasher
    public static String getMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = password.getBytes();
            md.update(bytes);
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
