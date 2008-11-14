package gov.nih.nci.cabig.caaers.validation.validator;

import gov.nih.nci.cabig.caaers.validation.PropertyUtil;
import gov.nih.nci.cabig.caaers.validation.annotation.Validator;
import gov.nih.nci.cabig.caaers.validation.annotation.ValidatorClass;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DomainObjectValidator implements ApplicationContextAware{
	
	private static final Log logger = LogFactory.getLog(DomainObjectValidator.class);
	private ApplicationContext applicationContext;
	private List<String> errors;
	
	
	/**
	    * This checks if the property is readable ?
	    * Will prevent the exception from being thrown. 
	    * @param beanWrapper - BeanWarapper, wrapping the command object
	    * @param propertyName - The property name to evaluate
	    * @return - false, in case of exception, otherwise will delegate to BeanWrapper.
	    */
	   	public static boolean isReadableProperty(BeanWrapper beanWrapper,String propertyName){
	   		try{
	   			return beanWrapper.isReadableProperty(propertyName);
	   		}catch(RuntimeException e){
	   			logger.warn("error while reading property[" + propertyName + "]", e);
	   		}
	   		return false;
	   	}
	   	
	    public List<String> validate(final Object command) {
	        if (command != null) {
	        	errors = new ArrayList<String>();
	            BeanWrapperImpl beanWrapperImpl = new BeanWrapperImpl(command);
	            Map<String, String> propertyMap = new LinkedHashMap<String, String>();
	            Map<String, String> collectionPropertyMap = new LinkedHashMap<String, String>();
	            PropertyDescriptor[] propertyDescriptors = beanWrapperImpl.getPropertyDescriptors();
	            String propertyName = null;
	            for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
	            	propertyName = propertyDescriptor.getName();
	            	if(isReadableProperty(beanWrapperImpl, propertyName)){
	            		errors = validateProperty(beanWrapperImpl,propertyName);
	            	}
	            		
            		//now check for collection properties
	                String collectionPropertyName = PropertyUtil.getColletionPropertyName(propertyName);
		                
	                if (collectionPropertyName != null && isReadableProperty(beanWrapperImpl,collectionPropertyName) && propertyMap.get(propertyName) == null) {
	                	//individual properties (items in collection)
	                    propertyMap.put(collectionPropertyName, propertyName);
	                    //collection properties.
	                    collectionPropertyMap.put(PropertyUtil.getCollectionMethodName(collectionPropertyName), propertyName);
	                }
	            }
		            
              //now validate the collection
	            for (String collectionPropertyName : propertyMap.keySet()) {
	                String propertyNameWhereErrorWillBeDisplayed = propertyMap.get(collectionPropertyName);
	                //logger.info("Found collection property:" + collectionPropertyName + " and property name where error messages(if any) will be displayed:" + propertyNameWhereErrorWillBeDisplayed);
	                String readMethodName = PropertyUtil.getCollectionMethodName(propertyNameWhereErrorWillBeDisplayed);
	                errors = validateCollectionProperty(readMethodName, beanWrapperImpl, collectionPropertyName, propertyNameWhereErrorWillBeDisplayed);

	            }
	            
	          //for UniqueObjectInCollection validator
	            for(String collectionProperty : collectionPropertyMap.keySet()){
	            	errors = validateCollectionProperty(collectionProperty, beanWrapperImpl, collectionProperty, null);
	            }
		                
            }
	        return errors;    
	    }

	    private List<String> validateCollectionProperty(String readMethodName, BeanWrapperImpl beanWrapperImpl, String propertyName, String propertyNameWhereErrorWillBeDisplayed) {
	        if (readMethodName != null) {

	            try {
					Annotation[] annotationsArray = beanWrapperImpl.getPropertyDescriptor(readMethodName).getReadMethod().getAnnotations();
					Object objectToValidate = beanWrapperImpl.getPropertyValue(propertyName);
					errors = validate(annotationsArray, objectToValidate, propertyNameWhereErrorWillBeDisplayed);
				} catch (InvalidPropertyException e) {
					logger.warn("Invalid property [readMethod:" + readMethodName + ", propertyName :" + propertyName + "]", e);
				}
	        }
	        return errors;
	    }

	    /**
	     *
	     * @param readMethodName method name which has the annotation present. it will be the same as property name for normal (non collection) properties. 
	     * @param beanWrapperImpl
	     * @param propertyName name of the property
	     * @param errors
	     * @param propertyNameWhereErrorWillBeDisplayed name of the proeprty where errros should be displayed.
	     */
	    private List<String> validateProperty(BeanWrapperImpl beanWrapperImpl, String propertyName) {
	        if (propertyName != null) {
	            try {
					Annotation[] annotationsArray = beanWrapperImpl.getPropertyDescriptor(propertyName).getReadMethod().getAnnotations();
					Object objectToValidate = beanWrapperImpl.getPropertyValue(propertyName);
					errors = validate(annotationsArray, objectToValidate,propertyName);
				} catch (BeansException e) {
					logger.warn("Invalid property [propertyName :" + propertyName + "]", e);
				}
	        }
	        return errors;
	    }


	    private List<String> validate(Annotation[] annotationsArray, Object propertyValue, String propertyName) {
	        for (Annotation validatorAnnotation : annotationsArray) {
	            Validator<Annotation> validator = createValidator(validatorAnnotation);

	            if (validator != null) {
	                logger.info("Found read property   property-value:" + propertyValue + " with validator :" + validator.getClass().getName());
	                if (!validator.validate(propertyValue)) {
	                    String errMsg = validator.message();
	                    errors.add(errMsg);
	                    logger.info("Found error code:" + errMsg + " for property:" + propertyName + " value:"
	                            + propertyValue + " & validator:" + validator.getClass().getName());
	                }
	            }
	        }
	        return errors;
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
	            if (validatorClasses != null && !validatorClasses.isEmpty()) {
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

	

	public void setApplicationContext(final ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}

}
