package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;

/**
 * @author Rhett Sutphin
 */
class PropertyNode extends TreeNode {
    private String propertyName;

    public PropertyNode(String propertyName) {
        if (propertyName == null) throw new NullPointerException("propertyName is required");
        this.propertyName = propertyName;
    }

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

    protected boolean isImmediatePropertyMatch(String immediatePropertyName) {
        return getPropertyName().equals(immediatePropertyName);
    }

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

    protected static String qualifyName(String base, String local) {
        return (base == null ? "" : base + '.') + local;
    }

    protected void addPropertyValues(String baseName, Object baseValue, MutablePropertyValues target) {
        target.addPropertyValue(qualifyName(baseName, getPropertyName()), nullSafePropertyValue(
                        baseValue, getPropertyName()));
    }

    protected static Object nullSafePropertyValue(Object bean, String propertyName) {
        return bean == null ? null : new BeanWrapperImpl(bean).getPropertyValue(propertyName);
    }

    @Override
    protected StringBuilder getPropertyPath(StringBuilder target) {
        super.getPropertyPath(target);

        if (target.length() > 0) target.append('.');
        target.append(getPropertyName());

        return target;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    
}
