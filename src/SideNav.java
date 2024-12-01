import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
public class SideNav extends JPanel {
    private final  DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

    public SideNav(ArrayList<String> information_schema, ArrayList<String> performance, ArrayList<String> tables) {        
        super();

        tables.forEach(x -> {
            DefaultMutableTreeNode table = new DefaultMutableTreeNode(x);

            if( x.equalsIgnoreCase("information_schema")){
                information_schema.forEach(y -> {
                    DefaultMutableTreeNode info = new DefaultMutableTreeNode(y);
                    table.add(info);
                });
            } else if(x.equalsIgnoreCase("performance_schema")){
                performance.forEach(y -> {
                    DefaultMutableTreeNode perf = new DefaultMutableTreeNode(y);
                    table.add(perf);
                });
            } 

            root.add(table);
        });

        JTree tree = new JTree(root);

        add(tree);
    }

}
