package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.ExternalAEReviewStatus;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    
    public void testUpdateStatus1() throws Exception{
    	
    	ExternalAdverseEvent externalAdverseEvent  = dao.getById(-1000);
    	assertNotNull(externalAdverseEvent);
    	assertNotNull(externalAdverseEvent.getExternalId());
    	assertEquals(ExternalAEReviewStatus.PENDING,externalAdverseEvent.getStatus());
    	
    	List<String> externalIds = new ArrayList<String>();
    	externalIds.add(externalAdverseEvent.getExternalId());
    	
    	dao.updateStatus( ExternalAEReviewStatus.PENDING, ExternalAEReviewStatus.IGNORED,externalIds);
    	interruptSession();
    	ExternalAdverseEvent updatedExternalAdverseEvent = dao.getById(-1000);
    	assertEquals(ExternalAEReviewStatus.IGNORED,updatedExternalAdverseEvent.getStatus());
    	
    }
    

    public void testUpdateStatus2() throws Exception{
    	
    	ExternalAdverseEvent externalAdverseEvent1  = dao.getById(-1000);
    	assertNotNull(externalAdverseEvent1);
    	assertNotNull(externalAdverseEvent1.getExternalId());
    	assertEquals(ExternalAEReviewStatus.PENDING,externalAdverseEvent1.getStatus());
    	
    	ExternalAdverseEvent externalAdverseEvent2  = new ExternalAdverseEvent();
    	externalAdverseEvent2.setAdverseEventTerm("term2");
    	externalAdverseEvent2.setAdverseEventTermCode("code2");
    	externalAdverseEvent2.setAdverseEventTermOtherValue("other value2");
    	externalAdverseEvent2.setExternalId("-2");
    	externalAdverseEvent2.setStartDate(new Date());
    	externalAdverseEvent2.setAttribution("device1");
    	String verbatim = "the subject had vomiting on so and so date becauese of device1";
    	externalAdverseEvent2.setVerbatim(verbatim);
    	externalAdverseEvent2.setGrade(Grade.NORMAL);
    	externalAdverseEvent2.setStatus(ExternalAEReviewStatus.REJECTED);
    	
    	dao.save(externalAdverseEvent2);
    	
    	
    	
    	List<String> externalIds = new ArrayList<String>();
    	externalIds.add(externalAdverseEvent1.getExternalId());
    	externalIds.add(externalAdverseEvent2.getExternalId());
    	Integer id2 = externalAdverseEvent2.getId();
    	
    	dao.updateStatus(ExternalAEReviewStatus.IGNORED, ExternalAEReviewStatus.PENDING, externalIds);
    	interruptSession();
    	ExternalAdverseEvent updatedExternalAdverseEvent1 = dao.getById(-1000);
    	ExternalAdverseEvent updatedExternalAdverseEvent2 = dao.getById(id2);
    	
    	assertEquals(ExternalAEReviewStatus.IGNORED,updatedExternalAdverseEvent1.getStatus());
    	assertEquals(ExternalAEReviewStatus.REJECTED,updatedExternalAdverseEvent2.getStatus());
    	
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
