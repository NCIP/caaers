/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.esb.client;

import org.springframework.context.ApplicationContextAware;

public interface ESBMessageConsumer extends ApplicationContextAware{
    public void processMessage(String message) throws Exception;
}
