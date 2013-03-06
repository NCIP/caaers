/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields.validators;


/**
 * @author Ion C. Olaru
 *
 */
public class IdentifierValidator extends FieldValidator {

    @Override
    public String getMessagePrefix() {
        return "Invalid identifier";
    }

    @Override
    public String getValidatorCSSClassName() {
        return "IDENTIFIER";
    }

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue != null) return fieldValue.toString().matches("^[a-zA-Z0-9#,*()_\\-'\":\\.{}\\[\\]]*$");
        return true;
    }

}
