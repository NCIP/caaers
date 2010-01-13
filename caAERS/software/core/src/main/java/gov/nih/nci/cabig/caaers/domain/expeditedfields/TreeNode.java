package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.MutablePropertyValues;

/**
 * @author Rhett Sutphin
 */
public abstract class TreeNode {
    protected final Log log = LogFactory.getLog(getClass());

    private TreeNode parent;

    private DisplayNameCreator displayNameCreator;

    private List<TreeNode> children;

    protected TreeNode() {
        children = new ArrayList<TreeNode>();
    }

    public static TreeNode property(String propertyName, String displayName, TreeNode... children) {
        TreeNode f = new PropertyNode(propertyName);
        f.setDisplayNameCreator(displayName == null ? null : new StaticDisplayNameCreator(displayName));
        return f.add(children);
    }

    public static TreeNode property(String propertyName, TreeNode... children) {
        return property(propertyName, null, children);
    }

    public static TreeNode list(String propertyName, String baseDisplayName, TreeNode... children) {
        return list(propertyName, new DefaultListDisplayNameCreator(baseDisplayName), children);
    }

    public static TreeNode list(String propertyName, DisplayNameCreator creator, TreeNode... children) {
        TreeNode f = new ListPropertyNode(propertyName).add(children);
        f.setDisplayNameCreator(creator);
        return f;
    }

    public static TreeNode section(ExpeditedReportSection section, TreeNode... children) {
        return new SectionNode(section).add(children);
    }

    public static TreeNode codedOrOther(String codedPropertyName, String codedDisplayName, String otherPropertyName, String otherDisplayName) {
        CodedOrOtherPropertyNode node = new CodedOrOtherPropertyNode(codedPropertyName, otherPropertyName);
        node.setCodedDisplayName(codedDisplayName);
        node.setOtherDisplayName(otherDisplayName);
        return node;
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

    protected StringBuilder getPropertyPath(StringBuilder target) {
        if (parent != null) {
            parent.getPropertyPath(target);
        }
        return target;
    }

    /**
     * Finds the {@link TreeNode} with the given property name, relative to this node. E.g., if this
     * node is "a.b" in the tree which contains "a.b.c.d.e", the following calls will succeed:
     * <ul>
     * <li><code>find("c")</code></li>
     * <li><code>find("c.d")</code></li>
     * <li><code>find("c.d.e")</code></li>
     * </ul>
     * 
     * @param desiredPropertyName
     */
    public TreeNode find(String desiredPropertyName) {
        for (TreeNode child : children) {
            TreeNode match = child.matchProperty(desiredPropertyName);
            if (match != null) return match;
        }
        return null;
    }

    /**
     * Finds the node (either this node or a child) which matches the given path. This is different
     * from {@link #find}, which only matches children.
     */
    protected abstract TreeNode matchProperty(String desiredPropertyName);

    public String getDisplayName(int index) {
        return getDisplayNameCreator().createGenericName();
    }

    public String getDisplayName() {
        return getDisplayNameCreator().createGenericName();
    }

    public void setDisplayName(String displayName) {
        setDisplayNameCreator(new StaticDisplayNameCreator(displayName));
    }

    /**
     * The qualified name will be displayName[of parent]~displayName[of this node]
     */
    public String getQualifiedDisplayName() {
        String name = (parent != null) ? parent.getQualifiedDisplayName() : "";
        String displayName = getDisplayName();
        if (displayName != null && displayName.length() > 0) name += ((name.length() > 0) ? "~" + displayName : displayName);
        return name;
    }

    /**
     * Find the property value(s) matching this node from the given object. These may not exactly
     * match the properties returned by a simple BeanWrapper. In particular, this will be true for
     * list properties (where the returned values will be values in the list, not the list itself)
     * and for coded-or-other properties (where the return value will be either the coded value or
     * the "other" value, whichever is set).
     * 
     * @param value
     * @return
     */
    public abstract MutablePropertyValues getPropertyValuesFrom(Object value);

    public abstract String getPropertyName();

    public boolean isList() {
        return false;
    }

    // /// BEAN ACCESSORS

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public DisplayNameCreator getDisplayNameCreator() {
        return displayNameCreator == null ? NullDisplayNameCreator.INSTANCE : displayNameCreator;
    }

    public void setDisplayNameCreator(DisplayNameCreator displayNameCreator) {
        this.displayNameCreator = displayNameCreator;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void recursivelyCollectListNodes(List<TreeNode> nodes){

        if(isLeaf()) return;
        if(isList()){
            nodes.add(this);
        }else{
          for(TreeNode node : children){
            node.recursivelyCollectListNodes(nodes);
          }  
        }

    }

    // //// OBJECT METHODS

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append('[').append(getPropertyName()).append(", ").append(getDisplayName()).append(']').toString();
    }

    public boolean isAncestorOf(TreeNode node) {
        if (isLeaf()) return false;
        if (getChildren().contains(node)) return true;
        for (TreeNode child : getChildren()) {
            if (child.isAncestorOf(node)) return true;
        }
        return false;
    }

    private static final class NullDisplayNameCreator implements DisplayNameCreator {
        public static final DisplayNameCreator INSTANCE = new NullDisplayNameCreator();

        private NullDisplayNameCreator() {
        }

        public String createIndexedName(int i) {
            return null;
        }

        public String createGenericName() {
            return null;
        }
    }
}
