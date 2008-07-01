package gov.nih.nci.cabig.caaers.web.fields;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;

import java.util.Map;

/**
 * Collects the basic information about a form field in a Spring MVC application. The goal is for
 * fields to be (mostly) rendered automatically and consistently, including labels and the
 * "required" indicator.
 * <p>
 * Also enables (but does not implement) some simple automatic validation (via the required flag).
 * 
 * @see TabWithFields
 * @see InputFieldGroup
 * @author Rhett Sutphin
 */
public interface InputField {
    String DETAILS = "details";

    String OPTIONS = "options";

    String SUBFIELDS = "subfields";

    String HELP = "help"; // refers to the help text key in messages.properties

    String SIZE = "size"; // size of the field (applied only for Text fields/AutoCompleters)

    // TODO: I don't think we need this; clear should probably be available for every autocompleter
    // - RMS20070725
    String ENABLE_CLEAR = "enableClear"; // enables the clear button (only for AutoCompleters)

    String COLS = "cols";

    String ROWS = "rows";

    String ENABLE_DELETE = "enableDelete"; // will put delete symbol near to the field(in
                                            // renderRow.tag).
    
    //Below fields are used for SPLIT_DATE kind of fields.
    String MONTH_REQUIRED = "mmRequired";
    String DAY_REQUIRED = "ddRequired";
    String YEAR_REQUIRED = "yyRequired";

    Category getCategory();

    /** @return the lowercased name of the value returned by {@link #getCategory} */
    String getCategoryName();

    String getDisplayName();

    boolean isRequired();

    void validate(BeanWrapper commandBean, Errors errors);

    /**
     * Returns an array of validators configured for this field.
     * 
     * @return {@link FieldValidator}
     */
    FieldValidator[] getValidators();

    String getPropertyName();

    Map<String, Object> getAttributes();

    enum Category {
        TEXT, TEXTAREA, DATE,SPLIT_DATE, SELECT, AUTOCOMPLETER, COMPOSITE, CHECKBOX, LONGSELECT, INPLACE_TEXT,LABEL
    }
}
