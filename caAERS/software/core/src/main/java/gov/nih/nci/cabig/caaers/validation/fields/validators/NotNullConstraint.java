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
@Constraint(validatedBy=NotNullValidator.class)
@Target( { METHOD, FIELD, ElementType.PARAMETER })
@Retention(RUNTIME)
public @interface NotNullConstraint {
	
    String message() default "Value missing";     
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    String fieldPath() default "";
}
