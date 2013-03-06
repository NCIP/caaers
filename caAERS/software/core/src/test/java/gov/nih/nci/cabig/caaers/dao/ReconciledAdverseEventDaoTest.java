/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.ReconciledAdverseEvent;

/**
 * @author Ramakrishna Gundala
 */
public class ReconciledAdverseEventDaoTest extends CaaersDbNoSecurityTestCase {
	
	private ReconciledAdverseEventDao dao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = (ReconciledAdverseEventDao) getDeployedApplicationContext().getBean("reconciledAdverseEventDao");
	}

    public void testGetById() throws Exception{
    	ReconciledAdverseEvent reconciledAdverseEvent  = dao.getById(-1000);
    	assertNotNull(reconciledAdverseEvent);
    }
    
}
