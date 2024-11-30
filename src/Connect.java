import java.sql.Connection;
import java.sql.DriverManager;

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

}