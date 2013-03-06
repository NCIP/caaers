/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.MutablePropertyValues;

 
/**
 * The Class TreeNode.
 *
 * @author Rhett Sutphin
 */
public abstract class TreeNode {
    
    /** The log. */
    protected final Log log = LogFactory.getLog(getClass());

    /** The parent. */
    private TreeNode parent;

    /** The display name creator. */
    private DisplayNameCreator displayNameCreator;

    /** The children. */
    private List<TreeNode> children;

    /**
     * Instantiates a new tree node.
     */
    protected TreeNode() {
        children = new ArrayList<TreeNode>();
    }

    /**
     * Property.
     *
     * @param propertyName the property name
     * @param displayName the display name
     * @param children the children
     * @return the tree node
     */
    public static TreeNode property(String propertyName, String displayName, TreeNode... children) {
        TreeNode f = new PropertyNode(propertyName);
        f.setDisplayNameCreator(displayName == null ? null : new StaticDisplayNameCreator(displayName));
        return f.add(children);
    }

    /**
     * Property.
     *
     * @param propertyName the property name
     * @param children the children
     * @return the tree node
     */
    public static TreeNode property(String propertyName, TreeNode... children) {
        return property(propertyName, null, children);
    }

    /**
     * List.
     *
     * @param propertyName the property name
     * @param baseDisplayName the base display name
     * @param children the children
     * @return the tree node
     */
    public static TreeNode list(String propertyName, String baseDisplayName, TreeNode... children) {
        return list(propertyName, new DefaultListDisplayNameCreator(baseDisplayName), children);
    }

    /**
     * List.
     *
     * @param propertyName the property name
     * @param creator the creator
     * @param children the children
     * @return the tree node
     */
    public static TreeNode list(String propertyName, DisplayNameCreator creator, TreeNode... children) {
        TreeNode f = new ListPropertyNode(propertyName).add(children);
        f.setDisplayNameCreator(creator);
        return f;
    }

    /**
     * Section.
     *
     * @param section the section
     * @param children the children
     * @return the tree node
     */
    public static TreeNode section(ExpeditedReportSection section, TreeNode... children) {
        return new SectionNode(section).add(children);
    }

    /**
     * Coded or other.
     *
     * @param codedPropertyName the coded property name
     * @param codedDisplayName the coded display name
     * @param otherPropertyName the other property name
     * @param otherDisplayName the other display name
     * @return the tree node
     */
    public static TreeNode codedOrOther(String codedPropertyName, String codedDisplayName, String otherPropertyName, String otherDisplayName) {
        CodedOrOtherPropertyNode node = new CodedOrOtherPropertyNode(codedPropertyName, otherPropertyName);
        node.setCodedDisplayName(codedDisplayName);
        node.setOtherDisplayName(otherDisplayName);
        return node;
    }

    /**
     * Checks if is leaf.
     *
     * @return true, if is leaf
     */
    public boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * Adds the.
     *
     * @param subnodes the subnodes
     * @return the tree node
     */
    public TreeNode add(TreeNode... subnodes) {
        for (TreeNode treeNode : subnodes) {
            this.children.add(treeNode);
            treeNode.setParent(this);
        }
        return this;
    }

    /**
     * Gets the property path.
     *
     * @return the property path
     */
    public String getPropertyPath() {
        return getPropertyPath(new StringBuilder()).toString();
    }

    /**
     * Gets the property path.
     *
     * @param target the target
     * @return the property path
     */
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
     * @param desiredPropertyName the desired property name
     * @return the tree node
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
     *
     * @param desiredPropertyName the desired property name
     * @return the tree node
     */
    protected abstract TreeNode matchProperty(String desiredPropertyName);

    /**
     * Gets the display name.
     *
     * @param index the index
     * @return the display name
     */
    public String getDisplayName(int index) {
        return getDisplayNameCreator().createGenericName();
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return getDisplayNameCreator().createGenericName();
    }

    /**
     * Sets the display name.
     *
     * @param displayName the new display name
     */
    public void setDisplayName(String displayName) {
        setDisplayNameCreator(new StaticDisplayNameCreator(displayName));
    }

    /**
     * The qualified name will be displayName[of parent]~displayName[of this node].
     *
     * @return the qualified display name
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
     * @param value the value
     * @return the property values from
     */
    public abstract MutablePropertyValues getPropertyValuesFrom(Object value);

    /**
     * Gets the property name.
     *
     * @return the property name
     */
    public abstract String getPropertyName();

    /**
     * Checks if is list.
     *
     * @return true, if is list
     */
    public boolean isList() {
        return false;
    }

    // /// BEAN ACCESSORS

    /**
     * Gets the parent.
     *
     * @return the parent
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * Sets the parent.
     *
     * @param parent the new parent
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * Gets the display name creator.
     *
     * @return the display name creator
     */
    public DisplayNameCreator getDisplayNameCreator() {
        return displayNameCreator == null ? NullDisplayNameCreator.INSTANCE : displayNameCreator;
    }

    /**
     * Sets the display name creator.
     *
     * @param displayNameCreator the new display name creator
     */
    public void setDisplayNameCreator(DisplayNameCreator displayNameCreator) {
        this.displayNameCreator = displayNameCreator;
    }

    /**
     * Gets the children.
     *
     * @return the children
     */
    public List<TreeNode> getChildren() {
        return children;
    }

    /**
     * Recursively collect list nodes.
     *
     * @param nodes the nodes
     */
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append('[').append(getPropertyName()).append(", ").append(getDisplayName()).append(']').toString();
    }

    /**
     * Checks if is ancestor of.
     *
     * @param node the node
     * @return true, if is ancestor of
     */
    public boolean isAncestorOf(TreeNode node) {
        if (isLeaf()) return false;
        if (getChildren().contains(node)) return true;
        for (TreeNode child : getChildren()) {
            if (child.isAncestorOf(node)) return true;
        }
        return false;
    }

    /**
     * The Class NullDisplayNameCreator.
     */
    private static final class NullDisplayNameCreator implements DisplayNameCreator {
        
        /** The Constant INSTANCE. */
        public static final DisplayNameCreator INSTANCE = new NullDisplayNameCreator();

        /**
         * Instantiates a new null display name creator.
         */
        private NullDisplayNameCreator() {
        }

        /* (non-Javadoc)
         * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DisplayNameCreator#createIndexedName(int)
         */
        public String createIndexedName(int i) {
            return null;
        }

        /* (non-Javadoc)
         * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.DisplayNameCreator#createGenericName()
         */
        public String createGenericName() {
            return null;
        }
    }
}
