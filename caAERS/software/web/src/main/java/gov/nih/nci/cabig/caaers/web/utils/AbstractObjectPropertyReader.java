package gov.nih.nci.cabig.caaers.web.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;


/**
 * @author Kruttik
 * 
 */
public abstract class AbstractObjectPropertyReader implements ObjectPropertyReader {

    private static final Logger logger = Logger.getLogger(AbstractObjectPropertyReader.class);

    private Object currentObject;

    private String propertyPath;

    private PropertyTokenHolder propertyTokenHolder;

    public boolean continueTraverse = true;

    public AbstractObjectPropertyReader(Object currentObject) {
        this(currentObject, null);
    }

    public AbstractObjectPropertyReader(Object currentObject, String objectPath) {
        this.currentObject = currentObject;
        this.propertyPath = objectPath;
        if (logger.isDebugEnabled()) {
            logger.debug("AbstractObjectPropertyTraverser(Object, String) - " + this); //$NON-NLS-1$
        }
    }

    public PropertyDescriptor getPropertyDescriptor(String nestedProperty) throws Exception {
        if (propertyTokenHolder == null) {
            propertyTokenHolder = getPropertyNameTokens(currentObject, nestedProperty);
        }
        return propertyTokenHolder.propertyDescriptor;
    }

    public Object getPropertyValueFromPath(String path) throws Exception {
        // TODO Auto-generated method stub
        this.propertyPath = path;
        return getPropertyValueFromPath();
    }

    public abstract Object getPropertyValueFromPath() throws Exception;

    public Object getPropertyValue(PropertyDescriptor pd) throws Exception {
        CustomMethodInvocater methodInvocater = new CustomMethodInvocater(currentObject, pd
                        .getReadMethod(), new Object[] {});
        return methodInvocater.invoke();
    }

    public abstract Object handleNullPropertyValue(Object nestedObject, int key) throws Exception;

    public Object applyKeys(Object value) throws Exception {
        for (int i = 0; i < propertyTokenHolder.keys.length; i++) {
            int key = Integer.parseInt(propertyTokenHolder.keys[i]);
            if (value == null) {
                continueTraverse = false;
                return null;
            }
            else if (value.getClass().isArray()) {
                if (Array.getLength(value) <= key) {
                    value = handleNullPropertyValue(value, key);
                }
                else {
                    value = Array.get(value, key);
                }
            }
            else if (value instanceof List) {
                List list = (List) value;
                if (list.size() <= key) {
                    value = handleNullPropertyValue(value, key);
                }
                else {
                    value = list.get(key);
                }
            }
            else if (value instanceof Set) {
                // Apply index to Iterator in case of a Set.
                Set set = (Set) value;
                int index = key;
                if (index < 0 || index >= set.size()) {
                    value = handleNullPropertyValue(value, key);
                }
                Iterator it = set.iterator();
                for (int j = 0; it.hasNext(); j++) {
                    Object elem = it.next();
                    if (j == index) {
                        value = elem;
                        break;
                    }
                }
            }
        }
        return value;
    }

    // ---------------------------------------------------------------------
    // Inner class for internal use
    // ---------------------------------------------------------------------

    private static class PropertyTokenHolder {

        public String canonicalName;

        public String actualName;

        public String[] keys;

        public PropertyDescriptor propertyDescriptor;

    }

    /**
     * @param obj
     * @param propertyName
     * @return
     * @throws Exception
     */
    private PropertyTokenHolder getPropertyNameTokens(Object obj, String propertyName)
                    throws Exception {
        PropertyTokenHolder tokens = new PropertyTokenHolder();
        String actualName = null;
        List keys = new ArrayList(2);
        int searchIndex = 0;
        while (searchIndex != -1) {
            int keyStart = propertyName.indexOf(PROPERTY_KEY_PREFIX, searchIndex);
            searchIndex = -1;
            if (keyStart != -1) {
                int keyEnd = propertyName.indexOf(PROPERTY_KEY_SUFFIX, keyStart
                                + PROPERTY_KEY_PREFIX.length());
                if (keyEnd != -1) {
                    if (actualName == null) {
                        actualName = propertyName.substring(0, keyStart);
                    }
                    String key = propertyName.substring(keyStart + PROPERTY_KEY_PREFIX.length(),
                                    keyEnd);
                    if ((key.startsWith("'") && key.endsWith("'"))
                                    || (key.startsWith("\"") && key.endsWith("\""))) {
                        key = key.substring(1, key.length() - 1);
                    }
                    keys.add(key);
                    searchIndex = keyEnd + PROPERTY_KEY_SUFFIX.length();
                }
            }
        }
        tokens.actualName = (actualName != null ? actualName : propertyName);
        tokens.canonicalName = tokens.actualName;
        tokens.propertyDescriptor = new PropertyDescriptor(tokens.actualName, obj.getClass());
        if (!keys.isEmpty()) {
            tokens.canonicalName += PROPERTY_KEY_PREFIX
                            + StringUtils.collectionToDelimitedString(keys, PROPERTY_KEY_SUFFIX
                                            + PROPERTY_KEY_PREFIX) + PROPERTY_KEY_SUFFIX;
            tokens.keys = StringUtils.toStringArray(keys);
        }
        return tokens;
    }

    public Object getCurrentObject() {
        return currentObject;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public PropertyTokenHolder getPropertyTokenHolder() {
        return propertyTokenHolder;
    }

    public String getActualName() {
        return this.propertyTokenHolder.actualName;
    }

    public String getCanonicalName() {
        return this.propertyTokenHolder.canonicalName;
    }

    public String[] getKeys() {
        return this.propertyTokenHolder.keys;
    }

    public PropertyDescriptor getPropertyDescriptor() {
        return this.propertyTokenHolder.propertyDescriptor;
    }
}
