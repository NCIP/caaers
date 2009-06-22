package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;

/**
 * @author Rhett Sutphin
 */
class ListPropertyNode extends PropertyNode {
    public ListPropertyNode(String propertyName) {
        super(propertyName);
    }

    @Override
    public boolean isList() {
        return true;
    }

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

    @Override
    protected StringBuilder getPropertyPath(StringBuilder target) {
        return super.getPropertyPath(target).append("[]");
    }

    @Override
    protected boolean isImmediatePropertyMatch(String immediatePropertyName) {
        return immediatePropertyName.replaceAll("\\[\\d*\\]", "").equals(getPropertyName());
    }

    @Override
    public String getDisplayName(int index) {
        return getDisplayNameCreator().createIndexedName(index);
    }
}
