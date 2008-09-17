package gov.nih.nci.cabig.caaers.web.fields.validators;

public class ZipCodeValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
        return fieldValue != null;
    }

    @Override
    public String getMessagePrefix() {
        return "Missing";
    }

    public String getValidatorCSSClassName() {
        return "ZIPCODE";
    }
}