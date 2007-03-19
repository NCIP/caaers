package gov.nih.nci.cabig.caaers.web.rule;

/**
 * @author Rhett Sutphin
 */
public interface InputField {
    Category getCategory();

    /** @return the lowercased name of the value returned by {@link #getCategory} */
    String getCategoryName();

    String getDisplayName();

    boolean isRequired();

    String getPropertyName();

    String getExtraInformation();

    enum Category {
        TEXT, TEXTAREA, DATE, SELECT, AUTOCOMPLETER
    }
}
