package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import java.util.ArrayList;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import edu.emory.mathcs.backport.java.util.Collections;

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
    
    /**
     * This method will return a qualified display name. 
     * ie. displayname [parent]~displayname [current node]
     * @return
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

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append('[').append(
                        getBeanPropertyName()).append(' ').append(getTreeNode()).append(']')
                        .toString();
    }
}
