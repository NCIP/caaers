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
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PersonDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.dto.ManageReportsRepotingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ListAdverseEventsControllerTest extends WebTestCase {

    private ListAdverseEventsController controller;
    private ListAdverseEventsCommand mockCommand;
    private StudyParticipantAssignmentDao assignmentDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
	private ReportDao reportDao;
	private PersonDao personDao;
    

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        studyDao = registerDaoMockFor(StudyDao.class);
        participantDao = registerDaoMockFor(ParticipantDao.class);
        reportDao = registerDaoMockFor(ReportDao.class);
        personDao = registerDaoMockFor(PersonDao.class);

        mockCommand = registerMockFor(ListAdverseEventsCommand.class);
        controller = new ListAdverseEventsController() {
            @Override
            protected Object formBackingObject(HttpServletRequest request) throws Exception {
                return mockCommand;
            }
        };
        controller.setAssignmentDao(assignmentDao);
        controller.setStudyDao(studyDao);
        controller.setParticipantDao(participantDao);
        controller.setReportDao(reportDao);
        controller.setPersonDao(personDao);
    }

    public void testIsFormSubmissionWithAssignment() throws Exception {
        request.addParameter("assignment", "foo");
        assertTrue(controller.isFormSubmission(request));
    }

    public void testIsFormSubmissionWithParticipantAndStudy() throws Exception {
        request.addParameter("participant", "foo");
        request.addParameter("study", "foo");
        assertTrue(controller.isFormSubmission(request));
    }

    public void testIsFormSubmissionWithMrnAndNci() throws Exception {
        request.addParameter("mrn", "foo");
        request.addParameter("nciIdentifier", "foo");
        assertTrue(controller.isFormSubmission(request));
    }

    public void testBindAssignment() throws Exception {
        StudyParticipantAssignment expectedAssignment = Fixtures.setId(3, new StudyParticipantAssignment());

        Participant p = new Participant();
        Study s = new LocalStudy();
        expectedAssignment.setParticipant(p);
        expectedAssignment.setStudySite(Fixtures.createStudySite(Fixtures.createOrganization("test"), 1));
        expectedAssignment.getStudySite().setStudy(s);
        String expectedGridId = "a-grid-id";
        
        ArrayList<Report> reports = new ArrayList<Report>();

        request.setParameter("studySubjectGridId", expectedGridId);
//        expect(assignmentDao.getByGridId(expectedGridId)).andReturn(expectedAssignment);
        expect(mockCommand.getStudy()).andReturn(s).anyTimes();
        expect(mockCommand.getParticipant()).andReturn(p).anyTimes();
        expect(mockCommand.getAssignment()).andStubReturn(expectedAssignment);
        expect(assignmentDao.getAssignment(p, s)).andReturn(expectedAssignment).anyTimes();
        expect(mockCommand.getSearchIdentifier()).andReturn("").anyTimes();
        expect(mockCommand.getReportStatus()).andReturn(ReportStatus.INPROCESS).anyTimes();
        expect(mockCommand.getMaxResults()).andReturn(15).anyTimes();
        expect(reportDao.search(s, p, ReportStatus.INPROCESS, "", 15)).andReturn(reports);
        expect(personDao.getByLoginId("SYSTEM")).andReturn(null);
        expect(mockCommand.getReports()).andReturn(reports).anyTimes();
        expect(mockCommand.getTotalResultsCount()).andReturn(10).anyTimes();
        LinkedList<ManageReportsRepotingPeriodDTO> mrrpd = new LinkedList<ManageReportsRepotingPeriodDTO>();
        mrrpd.add(new ManageReportsRepotingPeriodDTO(null));
        Map<String,  LinkedList<ManageReportsRepotingPeriodDTO>> testMap = new HashMap<>();
        expect(mockCommand.getFilteredResultMap()).andReturn(testMap);
        expect(mockCommand.getResultList()).andReturn(testMap).anyTimes();
        
        mockCommand.updateSubmittability();
        mockCommand.updateSubmittabilityBasedOnReportStatus();
        mockCommand.updateSubmittabilityBasedOnWorkflow();
        mockCommand.updateOptions();
        mockCommand.setSearchIdentifier(null);
        mockCommand.setMaxResults(15);
        mockCommand.setReports(reports);
        mockCommand.setUserId("SYSTEM");
        mockCommand.setStudyCentric(false);
        mockCommand.setParticipantCentric(false);
        mockCommand.populateResults((List<AdverseEventReportingPeriod>) EasyMock.anyObject());
        
        replayMocks();
        controller.handleRequest(request, response);
        verifyMocks();
    }
    
    //will test whether the security role is applied correctly.
    public void testDoSubmitAction() throws Exception{
        
        expect(mockCommand.getReports()).andStubReturn(new ArrayList<Report>());
        mockCommand.updateSubmittabilityBasedOnWorkflow();
        mockCommand.populateResults((List<AdverseEventReportingPeriod>) EasyMock.anyObject());
    	replayMocks();
    	controller.doSubmitAction(mockCommand);
    	verifyMocks();
    }
}
