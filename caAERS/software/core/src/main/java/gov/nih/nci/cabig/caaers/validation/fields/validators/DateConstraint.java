package gov.nih.nci.cabig.caaers.validation.fields.validators;

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
@Constraint(validatedBy=DateValueValidator.class)
@Target( { METHOD, FIELD, ElementType.PARAMETER })
@Retention(RUNTIME)
public @interface DateConstraint {

    String message() default "Invalid date";     
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    String fieldPath() default "";
}
