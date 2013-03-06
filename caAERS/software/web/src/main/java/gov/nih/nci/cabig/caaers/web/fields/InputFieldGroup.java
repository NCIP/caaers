/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public interface InputFieldGroup {
    String getName();

    String getDisplayName();

    List<InputField> getFields();
}
