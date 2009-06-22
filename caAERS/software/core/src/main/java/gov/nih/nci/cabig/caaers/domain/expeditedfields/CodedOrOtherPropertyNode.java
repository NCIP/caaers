package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import org.springframework.beans.MutablePropertyValues;

/**
 * @author Rhett Sutphin
 */
class CodedOrOtherPropertyNode extends PropertyNode {
    private String otherPropertyName;
    private String otherDisplayName;

    public CodedOrOtherPropertyNode(String codedPropertyName, String otherPropertyName) {
        super(codedPropertyName);
        this.otherPropertyName = otherPropertyName;
    }

    @Override
    protected void addPropertyValues(String baseName, Object baseValue, MutablePropertyValues target) {
        Object codedValue = nullSafePropertyValue(baseValue, getCodedPropertyName());
        Object otherValue = nullSafePropertyValue(baseValue, getOtherPropertyName());
        if (codedValue == null && otherValue != null) {
            target.addPropertyValue(qualifyName(baseName, getOtherPropertyName()), otherValue);
        } else {
            target.addPropertyValue(qualifyName(baseName, getCodedPropertyName()), codedValue);
        }
    }

    @Override
    protected boolean isImmediatePropertyMatch(String immediatePropertyName) {
        return immediatePropertyName.equals(getCodedPropertyName()) || immediatePropertyName.equals(getOtherPropertyName());
    }

    // //// BEAN PROPERTIES

    public String getCodedPropertyName() {
        return getPropertyName();
    }

    public String getOtherPropertyName() {
        return otherPropertyName;
    }

    public String getCodedDisplayName() {
        return getDisplayName();
    }

    public void setCodedDisplayName(String name) {
        setDisplayName(name);
    }

    public String getOtherDisplayName() {
        return otherDisplayName;
    }

    public void setOtherDisplayName(String otherDisplayName) {
        this.otherDisplayName = otherDisplayName;
    }
}
