package gov.nih.nci.cabig.caaers.validation.annotation;

import gov.nih.nci.cabig.caaers.validation.annotation.Validator;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * Link between an constraint annotation and it's validator implementation
 * 
 * @author Saurabh Agrawal, Created on August,11th, 2007
 * 
 */
@Documented
@Target( { ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidatorClass {
    Class<? extends Validator> value();
}
