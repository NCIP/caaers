package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;

 
/**
 * The Class PropertyNode.
 *
 * @author Rhett Sutphin
 */
class PropertyNode extends TreeNode {
    
    /** The property name. */
    private String propertyName;

    /**
     * Instantiates a new property node.
     *
     * @param propertyName the property name
     */
    public PropertyNode(String propertyName) {
        if (propertyName == null) throw new NullPointerException("propertyName is required");
        this.propertyName = propertyName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#matchProperty(java.lang.String)
     */
    @Override
    protected TreeNode matchProperty(String desiredPropertyName) {
        if (log.isDebugEnabled()) log.debug("Looking for " + desiredPropertyName + " in " + this);
        String[] bits = desiredPropertyName.split("\\.", 2);
        String immediatePropertyName = bits[0];
        String grandchildrenEtc = bits.length > 1 ? bits[1] : null;
        if (log.isDebugEnabled()) log.debug(" + " + immediatePropertyName + ' ' + grandchildrenEtc);
        if (isImmediatePropertyMatch(immediatePropertyName)) {
            if (log.isDebugEnabled()) log.debug(" + Matched immediate");
            if (bits.length == 1) {
                return this;
            } else {
                for (TreeNode child : getChildren()) {
                    TreeNode match = child.matchProperty(grandchildrenEtc);
                    if (match != null) return match;
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug(" - No property " + desiredPropertyName + " found as child of " + this);
        }
        return null;
    }

    /**
     * Checks if is immediate property match.
     *
     * @param immediatePropertyName the immediate property name
     * @return true, if is immediate property match
     */
    protected boolean isImmediatePropertyMatch(String immediatePropertyName) {
        return getPropertyName().equals(immediatePropertyName);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#getPropertyValuesFrom(java.lang.Object)
     */
    @Override
    public MutablePropertyValues getPropertyValuesFrom(Object value) {
        MutablePropertyValues these = new MutablePropertyValues();

        PropertyValues parentValues = null;
        if (getParent() != null) {
            parentValues = getParent().getPropertyValuesFrom(value);
        }

        if (parentValues == null) {
            addPropertyValues(null, value, these);
        } else {
            for (PropertyValue pv : parentValues.getPropertyValues()) {
                addPropertyValues(pv.getName(), pv.getValue(), these);
            }
        }
        return these;
    }

    /**
     * Qualify name.
     *
     * @param base the base
     * @param local the local
     * @return the string
     */
    protected static String qualifyName(String base, String local) {
        return (base == null ? "" : base + '.') + local;
    }

    /**
     * Adds the property values.
     *
     * @param baseName the base name
     * @param baseValue the base value
     * @param target the target
     */
    protected void addPropertyValues(String baseName, Object baseValue, MutablePropertyValues target) {
        target.addPropertyValue(qualifyName(baseName, getPropertyName()), nullSafePropertyValue(
                        baseValue, getPropertyName()));
    }

    /**
     * Null safe property value.
     *
     * @param bean the bean
     * @param propertyName the property name
     * @return the object
     */
    protected static Object nullSafePropertyValue(Object bean, String propertyName) {
        return bean == null ? null : new BeanWrapperImpl(bean).getPropertyValue(propertyName);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#getPropertyPath(java.lang.StringBuilder)
     */
    @Override
    protected StringBuilder getPropertyPath(StringBuilder target) {
        super.getPropertyPath(target);

        if (target.length() > 0) target.append('.');
        target.append(getPropertyName());

        return target;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#getPropertyName()
     */
    @Override
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the property name.
     *
     * @param propertyName the new property name
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    
}
