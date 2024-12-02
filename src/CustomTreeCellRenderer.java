import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
    private final ImageIcon databaseIcon;
    private final ImageIcon functionsIcon = new ImageIcon("b_routines.png");
    private final ImageIcon viewsIcon = new ImageIcon("b_browse.png");
    private final ImageIcon tablesIcon = new ImageIcon("s_views.png");

    public CustomTreeCellRenderer(ImageIcon databaseIcon) {
        this.databaseIcon = databaseIcon;
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            String nodeName = node.getUserObject().toString();

            // Set custom icons based on the node name
            switch (nodeName) {
                case "Functions":
                    setIcon(functionsIcon);
                    break;
                case "Procedures":
                    setIcon(functionsIcon);
                    break;
                case "Views":
                    setIcon(viewsIcon);
                    break;
                case "Tables":
                    setIcon(tablesIcon);
                    break;
                default:
                    setIcon(databaseIcon); // Default icon for other nodes
                    break;
            }
        }
        return this;
    }
}