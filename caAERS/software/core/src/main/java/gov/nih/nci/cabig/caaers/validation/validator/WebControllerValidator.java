package gov.nih.nci.cabig.caaers.validation.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.mvc.BaseCommandController;

/**
 * validator interface that should be used by controllers for validating the objects.
 * 
 * @author Biju Joseph, Created on December,7th, 2007
 */
public interface WebControllerValidator {
	
	/**
	 * A comma seperated list of collection parameter names.
	 */
	String ADDITIONAL_COLLECTIONS_PARAM = "ADDITIONAL_COLLECTIONS_PARAM";

    /**
     * Validate the domain object after. This method should be called in
     * {@link BaseCommandController#onBind} method to validate the form submission. If there are any
     * validation issues found, this method will update the errors object.
     * 
     * @param request
     *                the request
     * @param command
     *                the command object
     * @param errors
     *                the errors
     */
    public void validate(final HttpServletRequest request, final Object command,
                    final BindException errors);

}
