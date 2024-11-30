import java.util.ArrayList;

public class Info {
    private static final String INFO = "SELECT * FROM information_schema.TABLES";
    private static final String PERFORMANCE = "SELECT * FROM performance_schema.TABLES";
    private static final String YOURS = "SELECT schema_name FROM information_schema.schemata;";

    private Connect connect;

    private ArrayList<String> tables;
    private ArrayList<String> performance;
    private ArrayList<String> yours;
}
