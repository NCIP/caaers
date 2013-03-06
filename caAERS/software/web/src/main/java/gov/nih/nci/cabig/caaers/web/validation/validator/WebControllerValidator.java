/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.validation.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;

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
     * to validate the form submission. If there are any
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
