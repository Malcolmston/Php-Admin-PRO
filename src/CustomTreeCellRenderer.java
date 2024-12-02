import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
    private final ImageIcon folderIcon;

    public CustomTreeCellRenderer(ImageIcon icon) {
        super();
        this.folderIcon = icon;
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                  boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {
        // Get the default renderer's component
        Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        // Customize icon for non-leaf nodes (folders)
        if (!leaf && folderIcon != null) {
            setIcon(folderIcon);
        }

        return c;
    }
}