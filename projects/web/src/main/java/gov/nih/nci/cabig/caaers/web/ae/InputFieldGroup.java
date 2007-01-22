package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public interface InputFieldGroup {
    String getName();
    
    String getDisplayName();

    List<InputField> getFields();
}
