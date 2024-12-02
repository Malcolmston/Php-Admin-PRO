import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class SideNav extends JTree implements TreeExpansionListener {
    private final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");


    public SideNav(ArrayList<String> information_schema, ArrayList<String> performance, HashMap<String,ArrayList<String>> sys, ArrayList<String> tables) {
        super();
        DefaultMutableTreeNode addTable = new DefaultMutableTreeNode("Add Table");

        root.add(addTable);

        tables.forEach(x -> {
            DefaultMutableTreeNode table = new DefaultMutableTreeNode(x);
            if (x.equalsIgnoreCase("information_schema")) {
                information_schema.forEach(y -> {
                    DefaultMutableTreeNode info = new DefaultMutableTreeNode(y);
                    table.add(info);
                });
            } else if (x.equalsIgnoreCase("performance_schema")) {
                performance.forEach(y -> {
                    DefaultMutableTreeNode perf = new DefaultMutableTreeNode(y);
                    table.add(perf);
                });
            } else if (x.equalsIgnoreCase("sys")) {
                DefaultMutableTreeNode fns = new DefaultMutableTreeNode("Functions");
                DefaultMutableTreeNode prds = new DefaultMutableTreeNode("Procedures");
                DefaultMutableTreeNode vws = new DefaultMutableTreeNode("Views");
                DefaultMutableTreeNode tbls = new DefaultMutableTreeNode("Tables");

                fns.add(new DefaultMutableTreeNode(""));
                prds.add(new DefaultMutableTreeNode(""));
                vws.add(new DefaultMutableTreeNode(""));
                tbls.add(new DefaultMutableTreeNode(""));

                table.add(fns);
                table.add(prds);
                table.add(vws);
                table.add(tbls);

              
            } else {
                DefaultMutableTreeNode t = new DefaultMutableTreeNode("");
                table.add(t);

            }
            root.add(table);
        });
    

        setModel(new DefaultTreeModel(root));


        setShowsRootHandles(true);
        setRootVisible(false);

        addTreeExpansionListener(this);


        setCellRenderer(new CustomTreeCellRenderer());
    }
    

    @Override
    public void treeExpanded(TreeExpansionEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();

        if( node.getFirstChild().toString().trim().equals("") ) {
            collapsePath(event.getPath());
        }
        // Perform your desired action here
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {}
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Database Information");
        Connect con = new Connect(Connect.getUrl(), "malcolmstone", "Malcolmstone18$");
        Info info = new Info(con);
        
        // Populate side navigation with database tables
        ArrayList<String> information_schema = info.getTables();
        ArrayList<String> performance = info.getPerformance();
        ArrayList<String> tables = info.getYourTables();
        HashMap<String,ArrayList<String>>  sys = info.getSys();
        
        SideNav menu = new SideNav(information_schema, performance, sys,tables);

        JScrollPane scrollBar = new JScrollPane(menu);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollBar);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }



}