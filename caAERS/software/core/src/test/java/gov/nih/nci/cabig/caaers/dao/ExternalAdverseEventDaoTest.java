package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.ExternalAEReviewStatus;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;

import java.util.Date;

/**
 * @author Ramakrishna Gundala
 */
public class ExternalAdverseEventDaoTest extends CaaersDbNoSecurityTestCase {
	
	private ExternalAdverseEventDao dao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = (ExternalAdverseEventDao) getDeployedApplicationContext().getBean("externalAdverseEventDao");
	}

    public void testGetById() throws Exception{
    	
    	ExternalAdverseEvent externalAdverseEvent  = dao.getById(-1000);
    	assertNotNull(externalAdverseEvent);
    }
    
    public void testSave() throws Exception{
    	
    	ExternalAdverseEvent externalAdverseEvent  = new ExternalAdverseEvent();
    	externalAdverseEvent.setAdverseEventTerm("term2");
    	externalAdverseEvent.setAdverseEventTermCode("code2");
    	externalAdverseEvent.setAdverseEventTermOtherValue("other value2");
    	externalAdverseEvent.setExternalId("-2");
    	externalAdverseEvent.setStartDate(new Date());
    	externalAdverseEvent.setAttribution("device1");
    	String verbatim = "the subject had vomiting on so and so date becauese of device1";
    	externalAdverseEvent.setVerbatim(verbatim);
    	externalAdverseEvent.setGrade(Grade.NORMAL);
    	externalAdverseEvent.setStatus(ExternalAEReviewStatus.REJECTED);
    	
    	dao.save(externalAdverseEvent);
    	
    	interruptSession();
    	ExternalAdverseEvent savedExternalAdverseEvent  = dao.getById(externalAdverseEvent.getId());
    	assertNotNull(savedExternalAdverseEvent.getId());
    	assertEquals(verbatim,savedExternalAdverseEvent.getVerbatim());
    	assertEquals("device1",savedExternalAdverseEvent.getAttribution());
    	assertEquals(Grade.NORMAL,savedExternalAdverseEvent.getGrade());
    	assertEquals("term2",savedExternalAdverseEvent.getAdverseEventTerm());
    	assertEquals("code2",savedExternalAdverseEvent.getAdverseEventTermCode());
    	assertEquals(ExternalAEReviewStatus.REJECTED,savedExternalAdverseEvent.getStatus());
    }
    
}
