package gov.nih.nci.cabig.caaers.validation;

/**
 * @author Biju Joseph
 */
public class PropertyUtil {

    private static String PROPERTY_KEY_PREFIX = "[";

    private static char PROPERTY_KEY_PREFIX_CHAR = '[';

    private static String PROPERTY_KEY_SUFFIX = "]";

    private static char PROPERTY_KEY_SUFFIX_CHAR = ']';

    private static char DOT_CHAR = '.';

    public static String getCollectionMethodName(String propertyName) {

        if (propertyName == null || propertyName.indexOf(PROPERTY_KEY_PREFIX) == -1
                        || propertyName.indexOf(PROPERTY_KEY_SUFFIX) == -1
        // || propertyName.indexOf(DOT_CHAR) == -1
        ) {
            return null;
        }
        int keyStart = propertyName.lastIndexOf(PROPERTY_KEY_PREFIX);
        int keyEnd = propertyName.lastIndexOf(PROPERTY_KEY_SUFFIX);

        return propertyName.substring(0, keyStart);
    }

    public static String getColletionPropertyName(String propertyName) {

        if (propertyName == null || propertyName.indexOf(PROPERTY_KEY_PREFIX) == -1
                        || propertyName.indexOf(PROPERTY_KEY_SUFFIX) == -1
                        || propertyName.indexOf(DOT_CHAR) == -1) {
            return null;
        }
        int keyStart = propertyName.indexOf(DOT_CHAR);
        // propertyName.lastIndexOf(PROPERTY_KEY_PREFIX);
        int keyEnd = propertyName.lastIndexOf(PROPERTY_KEY_SUFFIX);

        return propertyName.substring(0, keyEnd + 1);
    }
    // public static String getMethodNameForColletionProperty(String propertyName) {
    // String actualName = null;
    // List keys = new ArrayList(2);
    // int searchIndex = 0;
    //
    // if (propertyName == null) {
    // return actualName;
    // }
    // while (searchIndex != -1) {
    // int keyStart = propertyName.indexOf(PROPERTY_KEY_PREFIX, searchIndex);
    // searchIndex = -1;
    // if (keyStart != -1) {
    // int keyEnd = propertyName.indexOf(PROPERTY_KEY_SUFFIX, keyStart +
    // PROPERTY_KEY_PREFIX.length());
    // if (keyEnd != -1) {
    // if (actualName == null) {
    // actualName = propertyName.substring(0, keyStart);
    // }
    // String key = propertyName.substring(keyStart + PROPERTY_KEY_PREFIX.length(), keyEnd);
    // if ((key.startsWith("'") && key.endsWith("'")) || (key.startsWith("\"") &&
    // key.endsWith("\""))) {
    // key = key.substring(1, key.length() - 1);
    // }
    // keys.add(key);
    // searchIndex = keyEnd + PROPERTY_KEY_SUFFIX.length();
    // }
    // }
    //
    // }
    //
    //
    // return actualName;
    // }

}
