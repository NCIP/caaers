package gov.nih.nci.cabig.caaers.validation.annotation;

import java.lang.annotation.Annotation;

/**
 * A constraint validator for a particular annotation
 * 
 * @author Biju Joseph, Created on December,7th, 2007
 * 
 */
public interface Validator<A extends Annotation> {
    /**
     * does the object/element pass the constraints
     */
    public boolean validate(Object value);

    /**
     * Take the annotations values
     * 
     * @param parameters
     */
    public void initialize(A parameters);

    public String message();
}
