package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.BeanWrapperImpl;

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
        String immediatePropertyName = bits[0].replaceAll("[\\[\\]]", "");
        String grandchildrenEtc = bits.length > 1 ? bits[1] : null;
        if (log.isDebugEnabled()) log.debug(" + " + immediatePropertyName + ' ' + grandchildrenEtc);
        if (getPropertyName().equals(immediatePropertyName)) {
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

    @Override
    public MutablePropertyValues getPropertyValuesFrom(Object value) {
        MutablePropertyValues these = new MutablePropertyValues();

        PropertyValues parentValues = null;
        if (getParent() != null) {
            parentValues = getParent().getPropertyValuesFrom(value);
        }

        if (parentValues == null) {
            addPropertyValues(getPropertyName(), value, these);
        } else {
            for (PropertyValue pv : parentValues.getPropertyValues()) {
                String thisName = (pv.getName() == null ? "" : pv.getName() + '.') + getPropertyName();
                addPropertyValues(thisName, pv.getValue(), these);
            }
        }
        return these;
    }

    protected void addPropertyValues(String qualifiedName, Object baseValue, MutablePropertyValues target) {
        if (baseValue == null) {
            target.addPropertyValue(qualifiedName, null);
        } else {
            target.addPropertyValue(qualifiedName,
                new BeanWrapperImpl(baseValue).getPropertyValue(getPropertyName()));
        }
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
