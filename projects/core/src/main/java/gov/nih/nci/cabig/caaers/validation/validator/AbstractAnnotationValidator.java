package gov.nih.nci.cabig.caaers.validation.validator;

import gov.nih.nci.cabig.caaers.validation.CaaersValidationException;
import gov.nih.nci.cabig.caaers.validation.annotation.Validatable;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.persistence.Embedded;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Jared Flatow
 */

public abstract class AbstractAnnotationValidator<AnnotationType extends Annotation> implements
                Validator {
    private static final Logger logger = Logger.getLogger("AnnotationValidator");

    private Class<AnnotationType> _annotationClass;

    public AbstractAnnotationValidator(Class<AnnotationType> annotationClass) {
        _annotationClass = annotationClass;
    }

    public abstract void validateAnnotated(AnnotationType annotation, String fieldName,
                    Object fieldValue);

    public boolean supports(Class klass) {
        return klass.isAnnotationPresent(Validatable.class);
    }

    public void validate(Object obj, Errors errors) {
        /* list the object's properties */
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(obj.getClass());
        logger.info("Validating " + _annotationClass.getName() + " annotations for: "
                        + obj.getClass());
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Method method = propertyDescriptor.getReadMethod();
            if (method != null) {
                try {
                    /* see if the property (getter) is annotated as required */
                    if (method.isAnnotationPresent(_annotationClass)) {
                        String fieldName = propertyDescriptor.getName();
                        logger.info("Found annotation for: " + fieldName);
                        AnnotationType annotation = (AnnotationType) method
                                        .getAnnotation(_annotationClass);
                        try {
                            validateAnnotated(annotation, fieldName, method.invoke(obj));
                        } catch (CaaersValidationException e) {
                            errors.reject(fieldName, e.getMessage());
                        }
                    } else if (method.isAnnotationPresent(Embedded.class)) {
                        /* perhaps this is an embedded object */
                        validate(method.invoke(obj), errors);
                    }
                } catch (IllegalAccessException e) {
                    errors.reject(e.getMessage());
                } catch (InvocationTargetException e) {
                    errors.reject(e.getMessage());
                }

            }
        }
    }
}
