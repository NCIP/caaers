package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Rhett Sutphin
 */
public class UnsatisfiedProperty {
    private TreeNode treeNode;

    private String beanPropertyName; // differs from from the node's propertyName for list nodes

    public UnsatisfiedProperty(TreeNode treeNode, String property) {
        this.treeNode = treeNode;
        this.beanPropertyName = property;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public String getBeanPropertyName() {
        return beanPropertyName;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append('[').append(
                        getBeanPropertyName()).append(' ').append(getTreeNode()).append(']')
                        .toString();
    }
}
