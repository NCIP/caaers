package gov.nih.nci.cabig.caaers.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@ValidatorClass(UniqueResearchStaffEmailAddressValidator.class)
@Target( { METHOD, FIELD, ElementType.PARAMETER })
@Retention(RUNTIME)
public @interface UniqueResearchStaffEmailAddress {
    int max() default Integer.MAX_VALUE;

    int min() default 0;

    String message() default "EmailAddress already in use";
}
