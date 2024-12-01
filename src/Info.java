import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Info {
    private static final String INFO = "SELECT * FROM information_schema.TABLES";
    private static final String PERFORMANCE = "SELECT * FROM performance_schema.TABLES";
    private static final String YOURS = "SELECT schema_name FROM information_schema.schemata;";

    private Connect connect;

    private ArrayList<String> tables;
    private ArrayList<String> performance;
    private ArrayList<String> yours;

    Info(Connect connect) {
        this.connect = connect;
        initInformation_schema();
        initPerformance_schema();
        initYours();
    }

    private void initInformation_schema() {
        this.tables = new ArrayList<>();

        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(INFO);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.tables.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    private void initPerformance_schema() {
        this.performance = new ArrayList<>();

        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(PERFORMANCE);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.performance.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initYours() {
        this.yours = new ArrayList<>();

        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(YOURS);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.yours.add(rs.getString("schema_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPerformance() {
        return tables;
    }

    public ArrayList<String> getTables() {
        return tables;
    }

    public  ArrayList<String> getYourTables() {
        return yours;
    }
}
