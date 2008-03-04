package gov.nih.nci.cabig.caaers.web.rule;

import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class DefaultSelectField extends AbstractInputField implements SelectField {
    private Map<Object, Object> options;

    public DefaultSelectField() {
    }

    public DefaultSelectField(String propertyName, String displayName, boolean required,
                    Map<Object, Object> options) {
        super(propertyName, displayName, required);
        this.options = options;
    }

    @Override
    public Category getCategory() {
        return Category.SELECT;
    }

    public Map<Object, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<Object, Object> options) {
        this.options = options;
    }
}
