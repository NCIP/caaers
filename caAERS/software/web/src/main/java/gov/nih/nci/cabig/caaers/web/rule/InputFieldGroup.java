package gov.nih.nci.cabig.caaers.web.rule;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public interface InputFieldGroup {
    String getName();

    String getDisplayName();

    List<InputField> getFields();
}
