package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;

 
/**
 * The Class ListPropertyNode.
 *
 * @author Rhett Sutphin
 */
class ListPropertyNode extends PropertyNode {
    
    /**
     * Instantiates a new list property node.
     *
     * @param propertyName the property name
     */
    public ListPropertyNode(String propertyName) {
        super(propertyName);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#isList()
     */
    @Override
    public boolean isList() {
        return true;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.PropertyNode#addPropertyValues(java.lang.String, java.lang.Object, org.springframework.beans.MutablePropertyValues)
     */
    @Override
    protected void addPropertyValues(String baseName, Object baseValue, MutablePropertyValues target) {
        if (baseValue == null) return;
        String qualifiedName = qualifyName(baseName, getPropertyName());
        BeanWrapperImpl wrappedValue = new BeanWrapperImpl(baseValue);
        List<?> thisProp = (List<?>) wrappedValue.getPropertyValue(getPropertyName());
        if (thisProp == null) return;
        for (int i = 0; i < thisProp.size(); i++) {
            Object o = thisProp.get(i);
            target.addPropertyValue(String.format("%s[%d]", qualifiedName, i), o);
        }
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.PropertyNode#getPropertyPath(java.lang.StringBuilder)
     */
    @Override
    protected StringBuilder getPropertyPath(StringBuilder target) {
        return super.getPropertyPath(target).append("[]");
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.PropertyNode#isImmediatePropertyMatch(java.lang.String)
     */
    @Override
    protected boolean isImmediatePropertyMatch(String immediatePropertyName) {
        return immediatePropertyName.replaceAll("\\[\\d*\\]", "").equals(getPropertyName());
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#getDisplayName(int)
     */
    @Override
    public String getDisplayName(int index) {
        return getDisplayNameCreator().createIndexedName(index);
    }
}
