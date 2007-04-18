package gov.nih.nci.cabig.caaers.web.fields;

import java.util.LinkedHashMap;

/**
 * @author Rhett Sutphin
 */
public class BooleanSelectField extends DefaultSelectField {
    private static final String DEFAULT_TRUE_DISPLAY = "Yes";
    private static final String DEFAULT_FALSE_DISPLAY = "No";

    public BooleanSelectField(String propertyName, String displayName, boolean required) {
        this(propertyName, displayName, required, DEFAULT_TRUE_DISPLAY, DEFAULT_FALSE_DISPLAY);
    }

    public BooleanSelectField(
        String propertyName, String displayName, boolean required,
        String trueDisplay, String falseDisplay
    ) {
        super(propertyName, displayName, required, new LinkedHashMap<Object, Object>());
        getOptions().put(Boolean.TRUE, trueDisplay);
        getOptions().put(Boolean.FALSE, falseDisplay);
    }
}
