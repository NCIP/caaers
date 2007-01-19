package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class MapSelectField extends SelectField {
    private Map<?, ?> options;

    public MapSelectField() { }

    public MapSelectField(String propertyName, String displayName, boolean required, Map<?, ?> options) {
        super(propertyName, displayName, required);
        this.options = options;
    }

    public String getType() {
        return "map-select";
    }

    public Map<?, ?> getOptions() {
        return options;
    }

    public void setOptions(Map<?, ?> options) {
        this.options = options;
    }
}
