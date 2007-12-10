package gov.nih.nci.cabig.caaers.validation.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * Orgnaizations must have unique nci identifier. This is required to create csm-protection-group etc..
 *
 * @author Biju Joseph, Created on December,6th, 2007
 */
@Documented
@ValidatorClass(UniqueNciIdentifierForOrganizationValidator.class)
@Target({METHOD, FIELD, ElementType.PARAMETER})
@Retention(RUNTIME)
public @interface UniqueNciIdentifierForOrganization {
    public abstract String message() default "nci identifier already exists in the database..!";

}