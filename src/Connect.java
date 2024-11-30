import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {
        // Method to establish database connection
        public static Connection getConnection(String url, String username, String password) throws SQLException {
            return DriverManager.getConnection(url, username, password);
        }
    
        private static String getUrl (String database) {
            return "jdbc:mysql://localhost:3306/" + database;
        }
    
        private static String getUrl (int port, String database) {
            return "jdbc:mysql://localhost:"+port+"/" + database;
        }
    
        private static String getUrl (String address, int port, String database) {
            return "jdbc:mysql://"+address+":"+port+"/" + database;
        }
    
    
        public static boolean isConnected (String database, String username, String password) {
            try (Connection connection = getConnection( getUrl(database), username, password)) {            
                // Example of a simple query (optional)
                String testQuery = "SELECT 1";
                try (PreparedStatement stmt = connection.prepareStatement(testQuery);
                     ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                       return true;
                    }
                }
            } catch (SQLException e) {
                System.err.println( e.getMessage());
                return false;
            }
    
            return false;
        }
    
        public static boolean isConnected (int port, String database, String username, String password) {
            try (Connection connection = getConnection( getUrl(port, database), username, password)) {            
                // Example of a simple query (optional)
                String testQuery = "SELECT 1";
                try (PreparedStatement stmt = connection.prepareStatement(testQuery);
                     ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                       return true;
                    }
                }
            } catch (SQLException e) {
                System.err.println( e.getMessage());
                return false;
            }
    
            return false;
        }
    
        public static boolean isConnected (String address, int port, String database, String username, String password) {
            try (Connection connection = getConnection( getUrl(address, port, database), username, password)) {            
                // Example of a simple query (optional)
                String testQuery = "SELECT 1";
                try (PreparedStatement stmt = connection.prepareStatement(testQuery);
                     ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                       return true;
                    }
                }
            } catch (SQLException e) {
                System.err.println( e.getMessage());
                return false;
            }
    
            return false;
        }
    
     public static String getErrorType(SQLException e) {
        if (e == null) return "Unknown Error";

        switch (e.getErrorCode()) {
            case 0:
                return "General Error";
            case 1045:
                return "Access Denied (Invalid Credentials)";
            case 1049:
                return "Unknown Database";
            case 1050:
                return "Table Already Exists";
            case 1054:
                return "Unknown Column";
            case 1062:
                return "Duplicate Entry";
            case 1064:
                return "SQL Syntax Error";
            case 1146:
                return "Table Does Not Exist";
            case 1451:
                return "Foreign Key Constraint Failure";
            default:
                return "Unclassified SQL Error (Code: " + e.getErrorCode() + ")";
        }
    }    
}