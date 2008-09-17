package gov.nih.nci.cabig.caaers.web.fields.validators;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class FieldValidatorTest extends AbstractTestCase {

    private FieldValidator numberValidator, dateValidator, notNullValidator, zipCodeValidator;

    @Override
    protected void setUp() throws Exception {

        numberValidator = FieldValidator.NUMBER_VALIDATOR;
        dateValidator = FieldValidator.DATE_VALIDATOR;
        notNullValidator = FieldValidator.NOT_NULL_VALIDATOR;
        zipCodeValidator = FieldValidator.ZIP_CODE_VALIDATOR;

    }

    public void testValidatorClassName() {

        assertEquals("validations.js needs NUMERIC validator class", "NUMERIC", numberValidator.getValidatorCSSClassName());
        assertEquals("validations.js needs DATE validator class", "DATE", dateValidator.getValidatorCSSClassName());
        assertEquals("validations.js needs NOTEMPTY validator class", "NOTEMPTY", notNullValidator.getValidatorCSSClassName());
        assertEquals("validations.js needs ZIPCODE validator class", "ZIPCODE", zipCodeValidator.getValidatorCSSClassName());

    }
}
