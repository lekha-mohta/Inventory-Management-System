package trainsheffield;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team047";
    private static final String DB_USER = "team047";
    private static final String DB_PASSWORD = "wu2Chog3o";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
