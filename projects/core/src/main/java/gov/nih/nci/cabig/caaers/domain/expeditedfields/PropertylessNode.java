package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import org.springframework.beans.MutablePropertyValues;

/**
 * @author Rhett Sutphin
 */
abstract class PropertylessNode extends TreeNode {
    @Override
    public String getPropertyName() {
        return null;
    }

    @Override
    public MutablePropertyValues getPropertyValuesFrom(Object value) {
        if (getParent() == null) return null;
        return getParent().getPropertyValuesFrom(value);
    }

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
