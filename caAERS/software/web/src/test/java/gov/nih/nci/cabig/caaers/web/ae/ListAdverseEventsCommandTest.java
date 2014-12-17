/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ListAdverseEventsCommandTest extends WebTestCase {
    private ListAdverseEventsCommand command;
    private ReportValidationService reportValidationService;
    private ResearchStaffDao researchStaffDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        researchStaffDao = registerDaoMockFor(ResearchStaffDao.class);
        reportValidationService = registerMockFor(ReportValidationService.class);
        command = new ListAdverseEventsCommand(reportValidationService, researchStaffDao);
        setupCommand();
    }
    
    private void setupCommand(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Report report1 = Fixtures.createReport("report 1");
        report1.setStatus(ReportStatus.INPROCESS);
    	report1.setId(1);
    	Report report2 = Fixtures.createReport("report 2");
        report2.setStatus(ReportStatus.INPROCESS);
    	report2.setId(2);
    	aeReport.addReport(report1);
    	aeReport.addReport(report2);
    	StudyParticipantAssignment assing = Fixtures.createAssignment();
    	assing.addReportingPeriod(Fixtures.createReportingPeriod());
    	assing.getReportingPeriods().get(0).addAeReport(aeReport);
    	
    	command.setReports(aeReport.getReports());
    	command.setAssignment(assing);
    }
    
    public void testUpdateSubmittability() throws Exception {
    	ReportSubmittability reportSubmittability1 = new ReportSubmittability();
    	ReportSubmittability reportSubmittability2 = new ReportSubmittability();
    	reportSubmittability2.addValidityMessage(ExpeditedReportSection.ATTRIBUTION_SECTION, "missing attribution");
    	expect(reportValidationService.isSubmittable(command.getAssignment().getAeReports().get(0).getReports().get(0))).andReturn(reportSubmittability1).times(0, 1);
    	expect(reportValidationService.isSubmittable(command.getAssignment().getAeReports().get(0).getReports().get(1))).andReturn(reportSubmittability2).times(0, 1);
    	replayMocks();
    	command.updateSubmittability();
    	verifyMocks();
    	assertNotNull(command.getReportsSubmittable());
    	assertTrue(command.getReportsSubmittable().get(1));
    	assertFalse(command.getReportsSubmittable().get(2));
    }
    
    public void testUpdateSubmittabilityBasedOnReportStatus() throws Exception{
    	command.getReportsSubmittable().clear();
    	command.getReportsSubmittable().put(1, true);
    	command.getReportsSubmittable().put(2, false);
    	command.getAssignment().getAeReports().get(0).getReports().get(0).setStatus(ReportStatus.COMPLETED);
    	command.getAssignment().getAeReports().get(0).getReports().get(1).setStatus(ReportStatus.PENDING);
    	command.updateSubmittabilityBasedOnReportStatus();
    	assertFalse(command.getReportsSubmittable().get(1));
    	assertFalse(command.getReportsSubmittable().get(2));
    }
    
    public void testExplicitStudyTrumps() throws Exception {
        Study expected = new LocalStudy();
        command.setStudy(expected);

        replayMocks();
        assertSame(expected, command.getStudy());
        verifyMocks();
    }



    public void testExplicitParticipantTrumps() throws Exception {
        Participant expected = new Participant();
        command.setParticipant(expected);

        replayMocks();
        assertSame(expected, command.getParticipant());
        verifyMocks();
    }

  
}
