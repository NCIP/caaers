package gov.nih.nci.cabig.caaers.web.fields;

import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public interface InputField {
    String DETAILS = "details";

    Category getCategory();

    /** @return the lowercased name of the value returned by {@link #getCategory} */
    String getCategoryName();

    String getDisplayName();

    boolean isRequired();

    String getPropertyName();

    Map<String, Object> getAttributes();

    enum Category {
        TEXT, TEXTAREA, DATE, SELECT, AUTOCOMPLETER
    }
}
