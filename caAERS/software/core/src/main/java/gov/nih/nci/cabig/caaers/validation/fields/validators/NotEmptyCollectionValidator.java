/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;


public class NotEmptyCollectionValidator extends FieldValidator<NotEmptyCollectionConstraint, Object> {

    @Override
    public boolean isValid(Object fieldValue) {
    	return (fieldValue != null && ((Collection)fieldValue).size() > 0);
    }

    @Override
    public String getMessagePrefix() {
        return "Incorrect size of the field ";
    }

    public String getValidatorCSSClassName() {
        return "NOTEMPTY";
    }
}
