package gov.nih.nci.cabig.caaers.web.fields;

import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class LongSelectField extends BaseSelectField {
    public LongSelectField() { }

    public LongSelectField(
        String propertyName, String displayName, boolean required, Map<Object, Object> options
    ) {
        super(propertyName, displayName, required, options);
    }

    @Override
    public Category getCategory() {
        return Category.LONGSELECT;
    }
}
