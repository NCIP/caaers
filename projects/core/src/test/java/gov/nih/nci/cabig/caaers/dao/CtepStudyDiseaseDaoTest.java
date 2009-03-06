package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import junit.framework.TestCase;

public class CtepStudyDiseaseDaoTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testDomainClass() {
		assertEquals(CtepStudyDisease.class, new CtepStudyDiseaseDao().domainClass());
	}

}
