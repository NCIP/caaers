/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CaaersStudyConsumerIntegrationTest extends CaaersTestCase {
	
	private CaaersStudyConsumer studyConsumer;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studyConsumer = (CaaersStudyConsumer) getDeployedApplicationContext().getBean("studyConsumer");
	}
	
	public void testBeanLoading(){
		assertNotNull(studyConsumer);
	}
	
	
}
