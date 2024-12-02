import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class SideNav extends JPanel {
    private final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
    private ImageIcon databaseImageIcon = new ImageIcon("s_db.png");


    public SideNav(ArrayList<String> information_schema, ArrayList<String> performance, ArrayList<String> tables) {
        super();
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
            } else {
                DefaultMutableTreeNode t = new DefaultMutableTreeNode("");
                table.add(t);

            }
            root.add(table);
        });
        
        JTree tree = new JTree(root);
        

        tree.setShowsRootHandles(true);
        tree.setRootVisible(false);

        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();

                if( node.getFirstChild().toString().trim().equals("") ) {
                    tree.collapsePath(event.getPath());
                }
                // Perform your desired action here
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                // Optional: Handle node collapse if needed
            }
        });


        tree.setCellRenderer(new CustomTreeCellRenderer(databaseImageIcon));

        add(tree);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Database Information");
        Connect con = new Connect(Connect.getUrl(), "malcolmstone", "Malcolmstone18$");
        Info info = new Info(con);
        
        // Populate side navigation with database tables
        ArrayList<String> information_schema = info.getTables();
        ArrayList<String> performance = info.getPerformance();
        ArrayList<String> tables = info.getYourTables();
        
        SideNav menu = new SideNav(information_schema, performance, tables);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menu);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}