/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public interface SelectField extends InputField {
    Map<Object, Object> getOptions();
}
