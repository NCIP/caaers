/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ExternalAdverseEventReportingPeriodQuery;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExternalAEReviewStatus;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;

import java.util.Date;
import java.util.List;

/**
 * @author Ramakrishna Gundala
 */
public class ExternalAdverseEventReportingPeriodDaoTest extends CaaersDbNoSecurityTestCase {
	
	private ExternalAdverseEventReportingPeriodDao dao;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = (ExternalAdverseEventReportingPeriodDao) getDeployedApplicationContext().getBean("externalAdverseEventReportingPeriodDao");
		adverseEventReportingPeriodDao = (AdverseEventReportingPeriodDao) getDeployedApplicationContext().getBean("adverseEventReportingPeriodDao");
	}

    public void testGetById() throws Exception{
    	
    	ExternalAdverseEventReportingPeriod externalAeReportingPeriod  = dao.getById(-1000);
    	assertNotNull(externalAeReportingPeriod);
    	assertNotNull(externalAeReportingPeriod.getAssignment());
    	assertNotNull(externalAeReportingPeriod.getAssignment().getId());
    	assertEquals(-1000,externalAeReportingPeriod.getAssignment().getId().intValue());
    	assertNotNull(externalAeReportingPeriod.getAssignment().getStudySite());
    }
    
   public void testSave() throws Exception{
    	
    	ExternalAdverseEventReportingPeriod externalAeReportingPeriod  = new ExternalAdverseEventReportingPeriod();
    	AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(-1000);
    	externalAeReportingPeriod.setDomainReportingPeriod(reportingPeriod);
    	externalAeReportingPeriod.setReviewStatus(ReviewStatus.APPROVED);
    	externalAeReportingPeriod.setName("Period2");
    	externalAeReportingPeriod.setStartDate(new Date());
    	externalAeReportingPeriod.setTreatmentAssignmentCode("Code2");
    	externalAeReportingPeriod.setOtherTreatmentAssignmentDescription("Description2");
    	
    	externalAeReportingPeriod.addExternalAdverseEvent(createExternalAdverseEvent());
    	
    	dao.save(externalAeReportingPeriod);
    	
    	ExternalAdverseEventReportingPeriod savedExternalAeReportingPeriod  = dao.getById(externalAeReportingPeriod.getId());
    	
    	assertNotNull(savedExternalAeReportingPeriod);
    	assertNotNull(savedExternalAeReportingPeriod.getId());
    	assertNotNull(savedExternalAeReportingPeriod.getAssignment());
    	assertEquals(-1000,savedExternalAeReportingPeriod.getAssignment().getId().intValue());
    	assertEquals(-1000,savedExternalAeReportingPeriod.getAssignment().getStudySite().getId().intValue());
    	assertEquals("Description2",savedExternalAeReportingPeriod.getOtherTreatmentAssignmentDescription());
    	assertEquals(ReviewStatus.APPROVED,savedExternalAeReportingPeriod.getReviewStatus());
    	assertEquals(1,savedExternalAeReportingPeriod.getExternalAdverseEvents().size());
    	assertEquals("term2",savedExternalAeReportingPeriod.getExternalAdverseEvents().get(0).getAdverseEventTerm());
    	assertEquals(Grade.NORMAL,savedExternalAeReportingPeriod.getExternalAdverseEvents().get(0).getGrade());
    	
    }
    
      public void testFilterByApproved() throws Exception{
	 
	 	ExternalAdverseEventReportingPeriodQuery query1 = new ExternalAdverseEventReportingPeriodQuery();
	 	query1.filterByApproved();
    	List<ExternalAdverseEventReportingPeriod> results  = dao.searchExternalAEReportingPeriods(query1);
    	assertEquals(0,results.size());
    	
    	ExternalAdverseEventReportingPeriodQuery query2 = new ExternalAdverseEventReportingPeriodQuery();
    	query2.filterByUnApproved();
    	results  = dao.searchExternalAEReportingPeriods(query2);
    	assertEquals(1,results.size());
    	
    	ExternalAdverseEventReportingPeriod externalAeReportingPeriod  = new ExternalAdverseEventReportingPeriod();
    	AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(-1000);
    	externalAeReportingPeriod.setDomainReportingPeriod(reportingPeriod);
    	externalAeReportingPeriod.setReviewStatus(ReviewStatus.APPROVED);
    	externalAeReportingPeriod.setName("Period2");
    	externalAeReportingPeriod.setStartDate(new Date());
    	externalAeReportingPeriod.setTreatmentAssignmentCode("Code2");
    	externalAeReportingPeriod.setOtherTreatmentAssignmentDescription("Description2");
    	externalAeReportingPeriod.setExternalId("-2");
    	
    	dao.save(externalAeReportingPeriod);
    	interruptSession();
    	results  = dao.searchExternalAEReportingPeriods(query1);
    	assertEquals(1,results.size());
    }
      
      
      public ExternalAdverseEvent createExternalAdverseEvent(){
    	  
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
        	
        	return externalAdverseEvent;
      }
    
}
