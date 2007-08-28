package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 */
public class TreeNode {
    protected final Log log = LogFactory.getLog(getClass());

    private TreeNode parent;
    private DisplayNameCreator displayNameCreator;
    private String propertyName;
    private boolean list;

    private List<TreeNode> children;

    protected TreeNode() {
        children = new ArrayList<TreeNode>();
    }

    public static TreeNode property(String propertyName, String displayName, TreeNode... children) {
        TreeNode f = new TreeNode();
        f.setPropertyName(propertyName);
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
        TreeNode f = property(propertyName, children);
        f.setDisplayNameCreator(creator);
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

    public static void listPropertyPaths(TreeNode node, List<String> paths){
    	if(node.getPropertyPath() != null)
    		paths.add(node.getPropertyPath());

    	for(TreeNode n : node.getChildren()){
    		listPropertyPaths(n, paths);
    	}
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

    /**
     * Finds the {@link TreeNode} with the given property name, relative to this node.  E.g.,
     * if this node is "a.b" in the tree which contains "a.b.c.d.e", the following calls will succeed:
     * <ul>
     *   <li><code>find("c")</code></li>
     *   <li><code>find("c.d")</code></li>
     *   <li><code>find("c.d.e")</code></li>
     * </ul>
     *
     * @param desiredPropertyName
     */
    public TreeNode find(String desiredPropertyName) {
        if (log.isDebugEnabled()) log.debug("Looking for " + desiredPropertyName + " in " + this);
        String[] bits = desiredPropertyName.split("\\.", 2);
        String immediatePropertyName = bits[0].replaceAll("[\\[\\]]", "");
        String grandchildrenEtc = bits.length > 1 ? bits[1] : null;
        for (TreeNode child : getChildren()) {
            if (log.isDebugEnabled()) log.debug(" + Examining child " + child);
            if (child.getPropertyName() == null) {
                // a section -- recurse into it, but only return if there's a match
                TreeNode match = child.find(desiredPropertyName);
                if (match != null) return match;
            } else {
                if (child.getPropertyName().equals(immediatePropertyName)) {
                    if (bits.length == 1) return child;
                    else return child.find(grandchildrenEtc);
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug(" - No property " + desiredPropertyName + " found as child of " + this);
        }
        return null;
    }

    public String getDisplayName(int index) {
        if (list) {
            return getDisplayNameCreator().createIndexedName(index);
        } else {
            return getDisplayNameCreator().createGenericName();
        }
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
    public String getQualifiedDisplayName(){
        String name = (parent != null) ? parent.getQualifiedDisplayName() : "";
        String displayName = getDisplayName();
        if (displayName != null && displayName.length() > 0)
            name += ( (name.length() > 0) ? "~" + displayName : displayName);
        return name;
    }

    ///// BEAN ACCESSORS

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public DisplayNameCreator getDisplayNameCreator() {
        return displayNameCreator == null ? NullDisplayNameCreator.INSTANCE : displayNameCreator;
    }

    public void setDisplayNameCreator(DisplayNameCreator displayNameCreator) {
        this.displayNameCreator = displayNameCreator;
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

    ////// OBJECT METHODS

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
            .append('[').append(getPropertyName())
            .append(", ").append(getDisplayName()).append(']')
            .toString();
    }

    private static final class NullDisplayNameCreator implements DisplayNameCreator {
        public static final DisplayNameCreator INSTANCE = new NullDisplayNameCreator();

        private NullDisplayNameCreator() { }

        public String createIndexedName(int i) {
            return null;
        }

        public String createGenericName() {
            return null;
        }
    }
}
