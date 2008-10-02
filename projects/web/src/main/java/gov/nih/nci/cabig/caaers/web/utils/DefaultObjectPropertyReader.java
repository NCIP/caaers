package gov.nih.nci.cabig.caaers.web.utils;

import org.springframework.beans.PropertyAccessorUtils;

public class DefaultObjectPropertyReader extends AbstractObjectPropertyReader {

    public DefaultObjectPropertyReader(Object currentObject, String objectPath) {
        super(currentObject, objectPath);
    }

    public DefaultObjectPropertyReader(Object currentObject) {
        super(currentObject);
    }

    @Override
    public Object handleNullPropertyValue(Object nestedObject, int key) throws Exception {
        return null;
    }

    public Object getPropertyValueFromPath() throws Exception {
        int pos = PropertyAccessorUtils.getFirstNestedPropertySeparatorIndex(getPropertyPath());
        // handle nested properties recursively
        if (pos > -1) {
            String nestedProperty = getPropertyPath().substring(0, pos);
            String nestedPath = getPropertyPath().substring(pos + 1);
            Object obj = getPropertyValue(getPropertyDescriptor(nestedProperty));
            if (getKeys() != null) {
                // apply indexes and map keys
                obj = applyKeys(obj);
            }
            if (continueTraverse) {
                DefaultObjectPropertyReader nestedDefaultObjectPropertyReader = new DefaultObjectPropertyReader(obj, nestedPath);
                return nestedDefaultObjectPropertyReader.getPropertyValueFromPath();
            }
        }
        else {
            Object obj = getPropertyValue(getPropertyDescriptor(getPropertyPath()));
            if (getKeys() != null) {
                // apply indexes and map keys
                obj = applyKeys(obj);
            }
            return obj;
        }
        return null;
    }
}
