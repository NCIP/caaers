package gov.nih.nci.cabig.caaers.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;

@Documented
@ValidatorClass(UniqueIdentifierForStudyValidator.class)
@Target( { METHOD, FIELD, ElementType.PARAMETER })
@Retention(RUNTIME)
public @interface UniqueIdentifierForStudy {
    int max() default Integer.MAX_VALUE;

    int min() default 0;

    String message() default "Identifier already exits in database";
}
