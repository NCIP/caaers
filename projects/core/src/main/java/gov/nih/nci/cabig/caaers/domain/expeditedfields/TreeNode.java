package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Rhett Sutphin
 */
public class TreeNode {
    private TreeNode parent;
    private String displayName;
    private String propertyName;
    private boolean list;

    private List<TreeNode> children;

    protected TreeNode() {
        children = new ArrayList<TreeNode>();
    }

    public static TreeNode property(String propertyName, String displayName, TreeNode... children) {
        TreeNode f = new TreeNode();
        f.setPropertyName(propertyName);
        f.setDisplayName(displayName);
        return f.add(children);
    }

    public static TreeNode property(String propertyName, TreeNode... children) {
        return property(propertyName, null, children);
    }

    public static TreeNode list(String propertyName, TreeNode... children) {
        return list(propertyName, null, children);
    }

    public static TreeNode list(String propertyName, String displayName, TreeNode... children) {
        TreeNode f = property(propertyName, displayName, children);
        f.setList(true);
        return f;
    }

    public static TreeNode section(String displayName, TreeNode... children) {
        TreeNode f = new TreeNode();
        f.setDisplayName(displayName);
        return f.add(children);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public TreeNode add(TreeNode... subnodes) {
        for (TreeNode treeNode : subnodes) {
            this.children.add(treeNode);
            treeNode.setParent(this);
        }
        return this;
    }

    public String getPropertyPath() {
        return getPropertyPath(new StringBuilder()).toString();
    }

    private StringBuilder getPropertyPath(StringBuilder target) {
        if (parent != null) {
            parent.getPropertyPath(target);
        }
        if (getPropertyName() != null) {
            if (target.length() > 0) target.append('.');
            target.append(getPropertyName());
            if (isList()) target.append("[]");
        }
        return target;
    }

    ///// BEAN ACCESSORS

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    /**
     * The qualified name will be displayName[of parent]~displayName[of this node]
     */
    public String getQualifiedDisplayName(){
    	String name = (parent != null)? parent.getQualifiedDisplayName() : "";
    	if(displayName != null && displayName.length() > 0)
    		name += ( (name.length() > 0)?"~" + displayName : displayName );
    	return name;
    }
}
