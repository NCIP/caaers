package gov.nih.nci.cabig.caaers.validation.validator;

import gov.nih.nci.cabig.caaers.validation.PropertyUtil;
import gov.nih.nci.cabig.caaers.validation.annotation.Validator;
import gov.nih.nci.cabig.caaers.validation.annotation.ValidatorClass;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Controller validation logic for validating objects.
 *
 * @author Biju Joseph, Created on December,7th, 2007
 */
public class WebControllerValidatorImpl implements ApplicationContextAware, WebControllerValidator {
    private final static Logger logger = Logger.getLogger(WebControllerValidatorImpl.class.getName());

    public void validate(final HttpServletRequest request, final Object command, final BindException errors) {

        if (request != null && command != null && errors != null) {
            BeanWrapperImpl beanWrapperImpl = new BeanWrapperImpl(command);

            Enumeration<String> propertyNames = request.getParameterNames();
            Set<String> collectionProperties = new HashSet<String>();
            while (propertyNames.hasMoreElements()) {
                String propertyName = propertyNames.nextElement();
                validateProperty(beanWrapperImpl, propertyName, errors);

                //now check for collection properties
                String collectionPropertyName = PropertyUtil.getNestedMethodNameForColletionProperty(propertyName);
                if (collectionPropertyName != null) {
                    collectionProperties.add(collectionPropertyName);
                }

            }

            //now validate the collection
            for (String collectionPropertyName : collectionProperties) {
                logger.info("Found collection property:" + collectionPropertyName);
                validateProperty(beanWrapperImpl, collectionPropertyName, errors);

            }

        }

    }

    private void validateProperty(final BeanWrapperImpl beanWrapperImpl, final String propertyName, final BindException errors) {
        if (beanWrapperImpl.isReadableProperty(propertyName)) {

            Annotation[] annotationsArray = beanWrapperImpl.getPropertyDescriptor(propertyName).getReadMethod()
                    .getAnnotations();

            for (Annotation validatorAnnotation : annotationsArray) {
                Validator<Annotation> validator = createValidator(validatorAnnotation);
                Object objectToValidate = beanWrapperImpl.getPropertyValue(propertyName);
                logger.info("Found read property having validation annotation. property-name:" + propertyName + "  property-value:" + objectToValidate);

                if (validator != null && !validator.validate(objectToValidate)) {
                    String errorCode = validator.message();
                    logger.info("Found error code:" + errorCode + " for property:" + propertyName + " value:"
                            + objectToValidate + " & annotation:" + validatorAnnotation.toString());

                    errors.rejectValue(propertyName, "REQUIRED", errorCode);
                }
            }
        }

    }

    /**
     * Creates the validator for a given annotation type.
     *
     * @param annotation the annotation
     * @return the validator
     */
    @SuppressWarnings("unchecked")
    private Validator<Annotation> createValidator(final Annotation annotation) {
        try {
            ValidatorClass validatorClass = annotation.annotationType().getAnnotation(ValidatorClass.class);
            if (validatorClass == null) {
                return null;
            }

            Map<String, Validator> validatorClasses = applicationContext.getBeansOfType(validatorClass.value());
            if (!validatorClasses.isEmpty()) {
                Validator validator = validatorClasses.get(validatorClasses.keySet().iterator().next());
                validator.initialize(annotation);
                logger.info("found validator " + validator.getClass() + " for annotation:" + annotation.toString());
                return validator;
            }

        }
        catch (Exception e) {
            throw new IllegalArgumentException("could not instantiate ClassValidator", e);
        }
        logger.info("No validator found for annotation:" + annotation.toString());
        return null;
    }

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private ApplicationContext applicationContext;

}
