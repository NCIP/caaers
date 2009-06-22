package gov.nih.nci.cabig.caaers.web.utils;

import java.beans.PropertyDescriptor;

public interface ObjectPropertyReader {
    /**
     * Path separator for nested properties. Follows normal Java conventions: getFoo().getBar()
     * would be "foo.bar".
     */
    String NESTED_PROPERTY_SEPARATOR = ".";

    char NESTED_PROPERTY_SEPARATOR_CHAR = '.';

    /**
     * Marker that indicates the start of a property key for an indexed or mapped property like
     * "person.addresses[0]".
     */
    String PROPERTY_KEY_PREFIX = "[";

    char PROPERTY_KEY_PREFIX_CHAR = '[';

    /**
     * Marker that indicates the end of a property key for an indexed or mapped property like
     * "person.addresses[0]".
     */
    String PROPERTY_KEY_SUFFIX = "]";

    char PROPERTY_KEY_SUFFIX_CHAR = ']';

    public Object getPropertyValue(PropertyDescriptor pd) throws Exception;

    public PropertyDescriptor getPropertyDescriptor(String nestedProperty) throws Exception;

    public Object getPropertyValueFromPath(String path) throws Exception;
}
