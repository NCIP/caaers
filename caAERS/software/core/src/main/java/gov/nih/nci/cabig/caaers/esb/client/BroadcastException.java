/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * Will be thrown when message cannot be broadcasted to the ESB
 * 
 * Taken from c3pr. Refactoring pending....
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class BroadcastException extends CaaersSystemException {
    public BroadcastException(String string) {
        super(string);
    }

    public BroadcastException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public BroadcastException(Throwable throwable) {
        super(throwable);
    }
}
