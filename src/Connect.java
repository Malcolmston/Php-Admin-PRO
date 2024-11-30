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
    
    
        private static boolean isConnected (String database, String username, String password) {
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
    
        private static boolean isConnected (int port, String database, String username, String password) {
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
    
        private static boolean isConnected (String address, int port, String database, String username, String password) {
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
    
}