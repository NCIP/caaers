package gov.nih.nci.cabig.caaers.validation.validator;

import gov.nih.nci.cabig.caaers.validation.CaaersValidationException;
import gov.nih.nci.cabig.caaers.validation.annotation.NumInRange;

/**
 * @author Jared Flatow
 */

public class NumInRangeValidator extends AbstractAnnotationValidator<NumInRange> {

    public NumInRangeValidator() {
        super(NumInRange.class);
    }

    public String message(NumInRange range) {
        return "Value is out of range [" + range.min() + ", " + range.max() + "]";
    }

    public void validateAnnotated(NumInRange range, String fieldName, Object fieldValue) {
        if (fieldValue == null) throw new CaaersValidationException(fieldName + ".required");
        /* Current implementation uses longs (but I wish there was a better way to deal with this) */
        Long value = ((Number) fieldValue).longValue();
        if (value < range.min() || value > range.max()) {
            throw new CaaersValidationException(message(range));
        }
    }
}
