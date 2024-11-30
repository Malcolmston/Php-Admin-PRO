import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {
    private String url;
    private String username;
    private String password;
    private Connection connection;

    public Connect(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public Connect(String url, String username) {
        this(url, username, "");
    }


        // Method to establish database connection
        public static Connection getConnection(String url, String username, String password) throws SQLException {
            return DriverManager.getConnection(url, username, password);
        }
    
        private static String getUrl () {
            return "jdbc:mysql://localhost:3306";
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
    
    
        public static boolean isConnected(Connection connection) throws SQLException {
            try (PreparedStatement stmt = connection.prepareStatement("SELECT 1");
                 ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }

     public static String getErrorType(SQLException e) {
        if (e == null) return "Unknown Error";

        switch (e.getErrorCode()) {
            case 0:
                return "General Error";
            case 1044:
                return "Access Denied (Invalid Hostname)";
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
                return e.getMessage() + " (Code: " + e.getErrorCode() + ")";
        }
    }    
}