package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import org.springframework.beans.MutablePropertyValues;

 
/**
 * The Class CodedOrOtherPropertyNode.
 *
 * @author Rhett Sutphin
 */
class CodedOrOtherPropertyNode extends PropertyNode {
    
    /** The other property name. */
    private String otherPropertyName;
    
    /** The other display name. */
    private String otherDisplayName;

    /**
     * Instantiates a new coded or other property node.
     *
     * @param codedPropertyName the coded property name
     * @param otherPropertyName the other property name
     */
    public CodedOrOtherPropertyNode(String codedPropertyName, String otherPropertyName) {
        super(codedPropertyName);
        this.otherPropertyName = otherPropertyName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.PropertyNode#addPropertyValues(java.lang.String, java.lang.Object, org.springframework.beans.MutablePropertyValues)
     */
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

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.PropertyNode#isImmediatePropertyMatch(java.lang.String)
     */
    @Override
    protected boolean isImmediatePropertyMatch(String immediatePropertyName) {
        return immediatePropertyName.equals(getCodedPropertyName()) || immediatePropertyName.equals(getOtherPropertyName());
    }

    // //// BEAN PROPERTIES

    /**
     * Gets the coded property name.
     *
     * @return the coded property name
     */
    public String getCodedPropertyName() {
        return getPropertyName();
    }

    /**
     * Gets the other property name.
     *
     * @return the other property name
     */
    public String getOtherPropertyName() {
        return otherPropertyName;
    }

    /**
     * Gets the coded display name.
     *
     * @return the coded display name
     */
    public String getCodedDisplayName() {
        return getDisplayName();
    }

    /**
     * Sets the coded display name.
     *
     * @param name the new coded display name
     */
    public void setCodedDisplayName(String name) {
        setDisplayName(name);
    }

    /**
     * Gets the other display name.
     *
     * @return the other display name
     */
    public String getOtherDisplayName() {
        return otherDisplayName;
    }

    /**
     * Sets the other display name.
     *
     * @param otherDisplayName the new other display name
     */
    public void setOtherDisplayName(String otherDisplayName) {
        this.otherDisplayName = otherDisplayName;
    }
}
