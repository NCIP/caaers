/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

public class ValidationException extends CaaersSystemException {
    private static final long serialVersionUID = 8625024692592257767L;

    public ValidationException(String message) {
        super(message);
    }
}
