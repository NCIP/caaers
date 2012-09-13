package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ExternalAdverseEventReportingPeriodQuery;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.util.Date;
import java.util.List;

/**
 * @author Ramakrishna Gundala
 */
public class ExternalAdverseEventReportingPeriodDaoTest extends CaaersDbNoSecurityTestCase {
	
	private ExternalAdverseEventReportingPeriodDao dao;
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = (ExternalAdverseEventReportingPeriodDao) getDeployedApplicationContext().getBean("externalAdverseEventReportingPeriodDao");
		studyParticipantAssignmentDao = (StudyParticipantAssignmentDao) getDeployedApplicationContext().getBean("studyParticipantAssignmentDao");
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
    	StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getById(-1000);
    	externalAeReportingPeriod.setAssignment(assignment);
    	externalAeReportingPeriod.setReviewStatus(ReviewStatus.APPROVED);
    	externalAeReportingPeriod.setName("Period2");
    	externalAeReportingPeriod.setStartDate(new Date());
    	externalAeReportingPeriod.setTreatmentAssignmentCode("Code2");
    	externalAeReportingPeriod.setTreatmentAssignmentDescription("Description2");
    	externalAeReportingPeriod.setExternalId("-2");
    	
    	dao.save(externalAeReportingPeriod);
    	
    	interruptSession();
    	
    	ExternalAdverseEventReportingPeriod savedExternalAeReportingPeriod  = dao.getById(externalAeReportingPeriod.getId());
    	
    	assertNotNull(savedExternalAeReportingPeriod);
    	assertNotNull(savedExternalAeReportingPeriod.getAssignment());
    	assertEquals(-1000,externalAeReportingPeriod.getAssignment().getId().intValue());
    	assertEquals(-1000,externalAeReportingPeriod.getAssignment().getStudySite().getId().intValue());
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
    	StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getById(-1000);
    	externalAeReportingPeriod.setAssignment(assignment);
    	externalAeReportingPeriod.setReviewStatus(ReviewStatus.APPROVED);
    	externalAeReportingPeriod.setName("Period2");
    	externalAeReportingPeriod.setStartDate(new Date());
    	externalAeReportingPeriod.setTreatmentAssignmentCode("Code2");
    	externalAeReportingPeriod.setTreatmentAssignmentDescription("Description2");
    	externalAeReportingPeriod.setExternalId("-2");
    	
    	dao.save(externalAeReportingPeriod);
    	interruptSession();
    	results  = dao.searchExternalAEReportingPeriods(query1);
    	assertEquals(1,results.size());
    }
    
}
