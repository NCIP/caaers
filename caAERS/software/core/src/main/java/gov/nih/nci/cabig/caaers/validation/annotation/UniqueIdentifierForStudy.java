package gov.nih.nci.cabig.caaers.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@ValidatorClass(UniqueIdentifierForStudyValidator.class)
@Constraint(validatedBy=UniqueIdentifierForStudyValidator.class)
@Target( { METHOD, FIELD, ElementType.PARAMETER })
@Retention(RUNTIME)
public @interface UniqueIdentifierForStudy {
    int max() default Integer.MAX_VALUE;

    int min() default 0;

    String message() default "Identifier already exits in database";     
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    String fieldPath() default "";
}
