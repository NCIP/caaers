package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import org.springframework.beans.MutablePropertyValues;

 
/**
 * The Class PropertylessNode.
 *
 * @author Rhett Sutphin
 */
abstract class PropertylessNode extends TreeNode {
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#getPropertyName()
     */
    @Override
    public String getPropertyName() {
        return null;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#getPropertyValuesFrom(java.lang.Object)
     */
    @Override
    public MutablePropertyValues getPropertyValuesFrom(Object value) {
        if (getParent() == null) return null;
        return getParent().getPropertyValuesFrom(value);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#matchProperty(java.lang.String)
     */
    @Override
    protected TreeNode matchProperty(String desiredPropertyName) {
        if (log.isDebugEnabled()) log.debug("Looking for " + desiredPropertyName + " in " + this);
        for (TreeNode child : getChildren()) {
            TreeNode match = child.matchProperty(desiredPropertyName);
            if (match != null) return match;
        }
        if (log.isDebugEnabled()) {
            log.debug(" - No property " + desiredPropertyName + " found as child of " + this);
        }
        return null;
    }
}
