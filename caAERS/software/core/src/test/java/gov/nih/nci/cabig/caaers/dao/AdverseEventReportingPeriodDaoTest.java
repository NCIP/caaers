package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.DateUtils;
import edu.nwu.bioinformatics.commons.testing.CoreTestCase;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventReportingPeriodForReviewQuery;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Sameer Sawant
 * @author Biju Joseph
 */

public class AdverseEventReportingPeriodDaoTest extends DaoNoSecurityTestCase<AdverseEventReportingPeriodDao> {
	
	private TreatmentAssignmentDao treatmentAssignmentDao = (TreatmentAssignmentDao) getApplicationContext()
    								.getBean("treatmentAssignmentDao");
	private StudyParticipantAssignmentDao assignmentDao = (StudyParticipantAssignmentDao) getApplicationContext()
    								.getBean("studyParticipantAssignmentDao");
	private EpochDao epochDao = (EpochDao) getApplicationContext()
									.getBean("epochDao");
	private AdverseEventDao adverseEventDao = (AdverseEventDao) getApplicationContext().getBean("adverseEventDao");
	private ExpeditedAdverseEventReportDao reportDao = (ExpeditedAdverseEventReportDao) getApplicationContext().getBean("expeditedAdverseEventReportDao");
	
    public void testGet() throws Exception {
        AdverseEventReportingPeriod reportingPeriod = getDao().getById(1001);
        CoreTestCase.assertDayOfDate("Wrong start date", 2008, Calendar.MAY, 23, reportingPeriod
                        .getStartDate());
        CoreTestCase.assertDayOfDate("Wrong end date", 2008, Calendar.MAY, 25, reportingPeriod
                        .getEndDate());
        CoreTestCase.assertEquals("test object 1001", reportingPeriod.getDescription());
        CoreTestCase.assertEquals("adverseEventReportingPeriodCode", reportingPeriod.getTreatmentAssignment().getCode());
        System.out.println(reportingPeriod.getReviewStatus());
    }
    
    public void testSave() throws Exception {
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setDescription("Save this reportingPeriod");
    	reportingPeriod.setStartDate(new Timestamp(DateUtils.createDate(2008, Calendar.MAY, 23)
                .getTime() + 600000));
    	reportingPeriod.setEndDate(new Timestamp(DateUtils.createDate(2008, Calendar.MAY, 25)
                .getTime() + 600000));
    	reportingPeriod.setTreatmentAssignment(treatmentAssignmentDao.getById(1001));
    	reportingPeriod.setEpoch(epochDao.getById(-1010));
    	reportingPeriod.setAssignment(assignmentDao.getById(-14));
    	
    	AdverseEvent ae = new AdverseEvent();
    	ae.setSolicited(true);
    	ae.setRequiresReporting(true);
    	CtcTerm term = new CtcTerm();
    	term.setId(3007);
    	
    	ae.setAdverseEventCtcTerm(Fixtures.createAdverseEventCtcTerm(ae, term));
    	
    	
    	reportingPeriod.addAdverseEvent(ae);
    	getDao().save(reportingPeriod);
        assertNotNull("No ID for new report", reportingPeriod.getId());
        int saveId = reportingPeriod.getId();
        interruptSession();
        AdverseEventReportingPeriod p = getDao().getById(saveId);
        p.getAdverseEvents().size();
        CoreTestCase.assertEquals("Save this reportingPeriod", p.getDescription());
        CoreTestCase.assertDayOfDate("Wrong start date", 2008, Calendar.MAY, 23, p.getStartDate());
        CoreTestCase.assertDayOfDate("Wrong end date", 2008, Calendar.MAY, 25, p.getEndDate());
        CoreTestCase.assertEquals(1, p.getAdverseEvents().size());
    }
    
    
    public void testModifyOrSaveReviewStatusAndComments() throws Exception {
    	
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setDescription("Save this reportingPeriod");
    	reportingPeriod.setStartDate(new Timestamp(DateUtils.createDate(2008, Calendar.MAY, 23)
                .getTime() + 600000));
    	reportingPeriod.setEndDate(new Timestamp(DateUtils.createDate(2008, Calendar.MAY, 25)
                .getTime() + 600000));
    	reportingPeriod.setTreatmentAssignment(treatmentAssignmentDao.getById(1001));
    	reportingPeriod.setEpoch(epochDao.getById(-1010));
    	reportingPeriod.setAssignment(assignmentDao.getById(-14));
    	
    	AdverseEvent ae = new AdverseEvent();
    	ae.setSolicited(true);
    	ae.setRequiresReporting(true);
    	CtcTerm term = new CtcTerm();
    	term.setId(3007);
    	
    	ae.setAdverseEventCtcTerm(Fixtures.createAdverseEventCtcTerm(ae, term));
    	
    	
    	reportingPeriod.addAdverseEvent(ae);
    	
    	
    	ReportingPeriodReviewComment reviewComment = new ReportingPeriodReviewComment();
		reviewComment.setUserComment("abcd");
		reviewComment.setCreatedDate(new Date());
		reviewComment.setAutoGeneratedText("Added by 4" );
		reviewComment.setEditable(true);
		reviewComment.setUserId("4");
		
		reportingPeriod.getReviewComments().add(reviewComment);
		
    	getDao().modifyOrSaveReviewStatusAndComments(reportingPeriod);
    	
    	
        assertNotNull("No ID for new report", reportingPeriod.getId());
        int saveId = reportingPeriod.getId();
        interruptSession();
        AdverseEventReportingPeriod p = getDao().getById(saveId);
        p.getAdverseEvents().size();
        CoreTestCase.assertEquals("Save this reportingPeriod", p.getDescription());
        CoreTestCase.assertDayOfDate("Wrong start date", 2008, Calendar.MAY, 23, p.getStartDate());
        CoreTestCase.assertDayOfDate("Wrong end date", 2008, Calendar.MAY, 25, p.getEndDate());
        CoreTestCase.assertEquals(1, p.getAdverseEvents().size());
        
        assertEquals("abcd", p.getReviewComments().get(0).getUserComment());
    }
    
    public void testSaveWithoutEndDate() throws Exception {
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setDescription("Save this reportingPeriod");
    	reportingPeriod.setStartDate(new Timestamp(DateUtils.createDate(2008, Calendar.MAY, 23)
                .getTime() + 600000));
    	
    	reportingPeriod.setTreatmentAssignment(treatmentAssignmentDao.getById(1001));
    	reportingPeriod.setEpoch(epochDao.getById(-1010));
    	reportingPeriod.setAssignment(assignmentDao.getById(-14));
    	
    	AdverseEvent ae = new AdverseEvent();
    	ae.setSolicited(true);
    	ae.setRequiresReporting(true);
    	CtcTerm term = new CtcTerm();
    	term.setId(3007);
    	
    	ae.setAdverseEventCtcTerm(Fixtures.createAdverseEventCtcTerm(ae, term));
    	
    	
    	reportingPeriod.addAdverseEvent(ae);
    	getDao().save(reportingPeriod);
        assertNotNull("No ID for new report", reportingPeriod.getId());
        int saveId = reportingPeriod.getId();
        interruptSession();
        AdverseEventReportingPeriod p = getDao().getById(saveId);
        p.getAdverseEvents().size();
        CoreTestCase.assertEquals("Save this reportingPeriod", p.getDescription());
        CoreTestCase.assertDayOfDate("Wrong start date", 2008, Calendar.MAY, 23, p.getStartDate());
        assertNull(p.getEndDate());
        CoreTestCase.assertEquals(1, p.getAdverseEvents().size());
    }
    
    public void testFindAdverseEventReportingPeriods() {
    	AdverseEventReportingPeriodForReviewQuery query = new AdverseEventReportingPeriodForReviewQuery();
    	query.filterByParticipant(1006);
    	List<AdverseEventReportingPeriod> reportingPeriods = getDao().findAdverseEventReportingPeriods(query);
    	assertNotNull(reportingPeriods);
    	assertEquals(2, reportingPeriods.size());
    	assertEquals(-17, (int)reportingPeriods.get(0).getAssignment().getId());
    }
    
    public void testFindAdverseEventReportingPeriods_WithStudyAswell() {
    	AdverseEventReportingPeriodForReviewQuery query = new AdverseEventReportingPeriodForReviewQuery();
    	query.filterByParticipant(1006);
    	query.filterByStudy(1001);
    	List<AdverseEventReportingPeriod> reportingPeriods = getDao().findAdverseEventReportingPeriods(query);
    	assertNotNull(reportingPeriods);
    	assertEquals(2, reportingPeriods.size());
    	assertEquals(-17, (int)reportingPeriods.get(0).getAssignment().getId());
    }

    public void testFindAdverseEventReportingPeriods_WithNonExistingStudy() {
    	AdverseEventReportingPeriodForReviewQuery query = new AdverseEventReportingPeriodForReviewQuery();
    	query.filterByParticipant(1006);
    	query.filterByStudy(1008);
    	List<AdverseEventReportingPeriod> reportingPeriods = getDao().findAdverseEventReportingPeriods(query);
    	assertNotNull(reportingPeriods);
    	assertEquals(0, reportingPeriods.size());
    }
    
    public void testFindAdverseEventReportingPeriods_WithOnlyStudy() {
    	AdverseEventReportingPeriodForReviewQuery query = new AdverseEventReportingPeriodForReviewQuery();
    	query.filterByStudy(1001);
    	System.out.println(query.getQueryString());
    	List<AdverseEventReportingPeriod> reportingPeriods = getDao().findAdverseEventReportingPeriods(query);
    	assertNotNull(reportingPeriods);
    	assertEquals(5, reportingPeriods.size());
    }
    

    public void testIsAdverseEventPresent(){
    	
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setId(1003);
    	
    	
    	AdverseEvent ae1 = new AdverseEvent();
    	ae1.setReportingPeriod(reportingPeriod);
    	
    	ae1.setId(-999);
    	AdverseEventCtcTerm ctcTerm = new AdverseEventCtcTerm();
    	CtcTerm term = new CtcTerm();
    	term.setId(3014);
    	ctcTerm.setTerm(term);
    	ae1.setAdverseEventCtcTerm(ctcTerm);
    	
    	boolean result = getDao().isAdverseEventPresent(ae1);
    	assertFalse(result);
    	
    }
    
    public void testIsAdverseEventPresent_SameAEId_DifferentTermId(){
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setId(1006);
    	AdverseEvent ae1 = new AdverseEvent();
    	ae1.setReportingPeriod(reportingPeriod);
    	
    	ae1.setId(-76);
    	AdverseEventCtcTerm ctcTerm = new AdverseEventCtcTerm();
    	CtcTerm term = new CtcTerm();
    	term.setId(3017);
    	ctcTerm.setTerm(term);
    	ae1.setAdverseEventCtcTerm(ctcTerm);
    	
    	boolean result = getDao().isAdverseEventPresent(ae1);
    	assertFalse(result);
    	
    }
    
    public void testIsAdverseEventPresent_SameAEId_SameTermId(){
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setId(1006);
    	AdverseEvent ae1 = new AdverseEvent();
    	ae1.setReportingPeriod(reportingPeriod);
    	ae1.setId(-76);
    	AdverseEventCtcTerm ctcTerm = new AdverseEventCtcTerm();
    	CtcTerm term = new CtcTerm();
    	term.setId(3012);
    	ctcTerm.setTerm(term);
    	ae1.setAdverseEventCtcTerm(ctcTerm);
    	
    	boolean result = getDao().isAdverseEventPresent(ae1);
    	assertFalse(result);
    	
    }
    
    public void testIsAdverseEventPresent_DifferentAEId_SameTermId(){
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setId(1006);
    	AdverseEvent ae1 = new AdverseEvent();
    	ae1.setReportingPeriod(reportingPeriod);
    	ae1.setId(-766);
    	AdverseEventCtcTerm ctcTerm = new AdverseEventCtcTerm();
    	CtcTerm term = new CtcTerm();
    	term.setId(3012);
    	ctcTerm.setTerm(term);
    	ae1.setAdverseEventCtcTerm(ctcTerm);
    	
    	boolean result = getDao().isAdverseEventPresent(ae1);
    	assertTrue(result);
    	
    }
    
    public void testIsAdverseEventPresent_DifferentAEId_SameTermId_DifferentLowLevelTerm(){
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setId(1006);
    	AdverseEvent ae1 = new AdverseEvent();
    	ae1.setReportingPeriod(reportingPeriod);
    	ae1.setId(-766);
    	AdverseEventCtcTerm ctcTerm = new AdverseEventCtcTerm();
    	CtcTerm term = new CtcTerm();
    	term.setId(3007);
    	ctcTerm.setTerm(term);
    	ae1.setAdverseEventCtcTerm(ctcTerm);
    	LowLevelTerm lowLevelTerm = new LowLevelTerm();
    	lowLevelTerm.setId(88);
    	ae1.setLowLevelTerm(lowLevelTerm);
    	
    	boolean result = getDao().isAdverseEventPresent(ae1);
    	assertFalse(result);
    	
    }
    
    public void testIsAdverseEventPresent_DifferentAEId_SameTermId_SameLowLevelTerm(){
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setId(1001);
    	AdverseEvent ae1 = new AdverseEvent();
    	ae1.setReportingPeriod(reportingPeriod);
    	ae1.setId(-766);
    	AdverseEventCtcTerm ctcTerm = new AdverseEventCtcTerm();
    	CtcTerm term = new CtcTerm();
    	term.setId(3007);
    	ctcTerm.setTerm(term);
    	ae1.setAdverseEventCtcTerm(ctcTerm);
    	LowLevelTerm lowLevelTerm = new LowLevelTerm();
    	lowLevelTerm.setId(-1);
    	ae1.setLowLevelTerm(lowLevelTerm);
    	
    	boolean result = getDao().isAdverseEventPresent(ae1);
    	assertTrue(result);
    	
    }
    
    public void testIsAdverseEventPresent_SameAEId_SameTermId_SameLowLevelTerm(){
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
    	reportingPeriod.setId(1001);
    	AdverseEvent ae1 = new AdverseEvent();
    	ae1.setReportingPeriod(reportingPeriod);
    	ae1.setId(-70);
    	AdverseEventCtcTerm ctcTerm = new AdverseEventCtcTerm();
    	CtcTerm term = new CtcTerm();
    	term.setId(3007);
    	ctcTerm.setTerm(term);
    	ae1.setAdverseEventCtcTerm(ctcTerm);
    	LowLevelTerm lowLevelTerm = new LowLevelTerm();
    	lowLevelTerm.setId(-1);
    	ae1.setLowLevelTerm(lowLevelTerm);
    	
    	boolean result = getDao().isAdverseEventPresent(ae1);
    	assertFalse(result);
    	
    }
    
    public void testReassociate(){
    	AdverseEventReportingPeriod rp = null;
    	{
    		rp = getDao().getById(1005);
    	}
    	interruptSession();
    	{
    		try{
    			rp.getAdverseEvents().size();
    			fail("Should throw lazy exception");
    		}catch(Exception e){
    			
    		}
    		getDao().reassociate(rp);
    		try{
    			rp.getAdverseEvents().size();
    			assertTrue(true);
    		}catch(Exception e){
    			fail("Should not throw lazy exception");
    		}
    	}
    }
    
    public void testGetByStudyParticipantAssignment(){
    	StudyParticipantAssignment assignment = new StudyParticipantAssignment();
    	assignment.setId(-17);
    	 List<AdverseEventReportingPeriod> rps = getDao().getByAssignment(assignment);
    	 assertEquals(2, rps.size());
    }
    
    //to test the descending ordering of adverse events.
    public void testGetAdverseEvents(){
    	AdverseEventReportingPeriod rp = getDao().getById(1005);
        assertEquals(-74, rp.getAdverseEvents().get(0).getId().intValue());
        assertEquals(-50, rp.getAdverseEvents().get(1).getId().intValue());
    }
}