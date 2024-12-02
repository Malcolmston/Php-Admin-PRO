import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Info {
    private static final String INFO = "SELECT * FROM information_schema.TABLES";
    private static final String PERFORMANCE = "SELECT * FROM performance_schema.TABLES";
    private static final String YOURS = "SELECT schema_name FROM information_schema.schemata;";
    private static final String SYS = "SELECT 'Function' AS object_type, ROUTINE_NAME AS name FROM information_schema.ROUTINES WHERE ROUTINE_SCHEMA = 'sys' AND ROUTINE_TYPE = 'FUNCTION'\n" + //
                "UNION ALL\n" + //
                "SELECT 'Procedure' AS object_type, ROUTINE_NAME AS name FROM information_schema.ROUTINES WHERE ROUTINE_SCHEMA = 'sys' AND ROUTINE_TYPE = 'PROCEDURE'\n" + //
                "UNION ALL\n" + //
                "SELECT 'View' AS object_type, TABLE_NAME AS name FROM information_schema.VIEWS WHERE TABLE_SCHEMA = 'sys'\n" + //
                "UNION ALL\n" + //
                "SELECT 'Table' AS object_type, TABLE_NAME AS name FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'sys';";

    private Connect connect;

    private ArrayList<String> tables;
    private ArrayList<String> performance;
    private ArrayList<String> yours;
    private HashMap<String, ArrayList<String>> sys;

    Info(Connect connect) {
        this.connect = connect;
        initInformation_schema();
        initPerformance_schema();
        initYours();
        iniSys();
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

    private void setUpMap () {
        HashMap<String, ArrayList<String>> m = new HashMap<>();
        m.put("Functions", new ArrayList<>());
        m.put("Procedures", new ArrayList<>());
        m.put("Views", new ArrayList<>());
        m.put("Tables", new ArrayList<>());

        this.sys = m;
    }

    private void iniSys() {
        this.setUpMap();


        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(SYS);
            ResultSet rs = stmt.executeQuery();

            ArrayList< HashMap<String, String>> list = new ArrayList<>();


            while (rs.next()) {

                String item = rs.getString("object_type");

                if( item.equalsIgnoreCase("Function")) {
                    this.sys.get("Functions").add(rs.getString("name"));
                } else if (item.equalsIgnoreCase("Procedure")) {
                    this.sys.get("Procedures").add(rs.getString("name"));
                } else if (item.equalsIgnoreCase("View")) {
                    this.sys.get("Views").add(rs.getString("name"));
                } else if (item.equalsIgnoreCase("Table")) {
                    this.sys.get("Tables").add(rs.getString("name"));
                }

          
            }

            list.forEach(item -> {

                if( item.get("object_type").equalsIgnoreCase("Function")) {
                    sys.get(0).add(item.get("name"));
                } else if (item.get("object_type").equalsIgnoreCase("Procedure")) {
                    sys.get(1).add(item.get("name"));
                } else if (item.get("object_type").equalsIgnoreCase("View")) {
                    sys.get(2).add(item.get("name"));
                } else if (item.get("object_type").equalsIgnoreCase("Table")) {
                    sys.get(3).add(item.get("name"));
                }
            });


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

    public HashMap<String,ArrayList<String>> getSys() {
        return sys;
    }
}
