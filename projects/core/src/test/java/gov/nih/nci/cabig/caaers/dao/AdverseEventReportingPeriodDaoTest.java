package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.DateUtils;
import edu.nwu.bioinformatics.commons.testing.CoreTestCase;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * 
 * @author Sameer Sawant
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
    
}