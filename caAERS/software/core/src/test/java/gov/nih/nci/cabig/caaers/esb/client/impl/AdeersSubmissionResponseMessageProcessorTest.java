/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdeersSubmissionResponseMessageProcessorTest extends CaaersTestCase {
	AdeersSubmissionResponseMessageProcessor processor;
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testLoadingOfBean(){
		processor = (AdeersSubmissionResponseMessageProcessor) getDeployedApplicationContext().getBean("submitAEDataXMLAsAttachmentResponse");
		assertNotNull(processor);
	}
}
