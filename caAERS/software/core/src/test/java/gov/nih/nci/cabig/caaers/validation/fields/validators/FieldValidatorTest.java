/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.validation.fields.validators.FieldValidator;

/**
 * @author Biju Joseph
 */
public class FieldValidatorTest extends AbstractTestCase {

    private FieldValidator numberValidator, dateValidator, notNullValidator, zipCodeValidator;

    @Override
    protected void setUp() throws Exception {

        numberValidator = FieldValidator.NUMBER_VALIDATOR;
        dateValidator = FieldValidator.PAST_DATE_VALIDATOR;
        notNullValidator = FieldValidator.NOT_NULL_VALIDATOR;
        zipCodeValidator = FieldValidator.ZIP_CODE_VALIDATOR;

    }

    public void testValidatorClassName() {

        assertEquals("validations.js needs NUMERIC validator class", "NUMERIC", numberValidator.getValidatorCSSClassName());
        assertEquals("validations.js needs DATE validator class", "DATE", dateValidator.getValidatorCSSClassName());
        assertEquals("validations.js needs NOTEMPTY validator class", "NOTEMPTY", notNullValidator.getValidatorCSSClassName());
        assertEquals("validations.js needs ZIPCODE validator class", "ZIP_POSTAL_CODE", zipCodeValidator.getValidatorCSSClassName());

    }
}
