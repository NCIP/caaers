/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * @author Jared Flatow
 */
public class CaaersValidationException extends CaaersSystemException {
    public CaaersValidationException(String message) {
        super(message);
    }
}
