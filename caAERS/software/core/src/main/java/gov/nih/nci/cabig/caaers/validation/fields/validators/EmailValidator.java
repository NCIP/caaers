/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import org.apache.commons.validator.GenericValidator;


public class EmailValidator extends FieldValidator<EmailConstraint, Object> {

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue == null) return true; // null email is considered as valid
        return GenericValidator.isEmail(fieldValue.toString());
    }

    @Override
    public String getMessagePrefix() {
        return "Invalid";
    }

    public String getValidatorCSSClassName() {
        return "EMAIL";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
