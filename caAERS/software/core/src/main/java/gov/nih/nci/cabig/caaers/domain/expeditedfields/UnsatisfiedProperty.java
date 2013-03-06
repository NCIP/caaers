/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import java.util.ArrayList;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import java.util.Collections;

 
/**
 * The Class UnsatisfiedProperty.
 *
 * @author Rhett Sutphin
 */
public class UnsatisfiedProperty {
    
    /** The tree node. */
    private TreeNode treeNode;

    /** The bean property name. */
    private String beanPropertyName; // differs from from the node's propertyName for list nodes

    /**
     * Instantiates a new unsatisfied property.
     *
     * @param treeNode the tree node
     * @param property the property
     */
    public UnsatisfiedProperty(TreeNode treeNode, String property) {
        this.treeNode = treeNode;
        this.beanPropertyName = property;
    }

    /**
     * Gets the tree node.
     *
     * @return the tree node
     */
    public TreeNode getTreeNode() {
        return treeNode;
    }

    /**
     * Gets the bean property name.
     *
     * @return the bean property name
     */
    public String getBeanPropertyName() {
        return beanPropertyName;
    }
    
    /**
     * This method will return a qualified display name.
     * ie. displayname [parent]~displayname [current node]
     *
     * @return the display name
     */
    public String getDisplayName(){
    	ArrayList<String> displayNameList = new ArrayList<String>();
    	
    	TreeNode node = treeNode;
    	while (node != null && node instanceof PropertyNode && StringUtils.isNotEmpty(node.getDisplayName())){
    		displayNameList.add(node.getDisplayName());
    		node = node.getParent();
    	}
    	Collections.reverse(displayNameList);
    	return StringUtils.join(displayNameList, "~");
    	
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append('[').append(
                        getBeanPropertyName()).append(' ').append(getTreeNode()).append(']')
                        .toString();
    }
}
