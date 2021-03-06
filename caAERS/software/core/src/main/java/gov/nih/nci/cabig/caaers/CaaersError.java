/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers;

/**
 * @author Rhett Sutphin
 */
public class CaaersError extends Error {
    public CaaersError(String message) {
        super(message);
    }

    public CaaersError(String message, Throwable cause) {
        super(message, cause);
    }
}
