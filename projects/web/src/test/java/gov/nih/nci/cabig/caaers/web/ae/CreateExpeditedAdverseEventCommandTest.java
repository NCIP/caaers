package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import org.easymock.EasyMock;

import java.util.Arrays;
import java.util.List;

/**
 * This class also contains tests for the shared behavior in AbstractExpeditedAdverseEventInputCommand
 *
 * @author Rhett Sutphin
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class CreateExpeditedAdverseEventCommandTest extends AeWebTestCase {
    @Override
    protected CreateExpeditedAdverseEventCommand createCommand() {
        return createRealCommand();
    }

    public void testCreatedExpeditedReportHasExactlyOneAE() throws Exception {
        assertEquals(1, command.getAeReport().getAdverseEvents().size());
    }

    protected void expectGetAssignment(StudyParticipantAssignment spa) {
        EasyMock.expect(assignmentDao.getAssignment(command.getParticipant(), command.getStudy()))
            .andReturn(spa).anyTimes();
    }

    public void testGetAssignment() throws Exception {
        StudyParticipantAssignment expectedAssignment = new StudyParticipantAssignment();

        command.setStudy(new Study());
        command.setParticipant(new Participant());
        EasyMock.expectLastCall().andReturn(expectedAssignment);
        expectGetAssignment(expectedAssignment);

        replayMocks();
        assertSame(expectedAssignment, command.getAssignment());
        verifyMocks();
    }
    
    public void testGetAssignmentNullWhenStudyNull() throws Exception {
        command.setParticipant(new Participant());
        replayMocks();
        assertNull(command.getAssignment());
        verifyMocks();
    }

    public void testGetAssignmentNullWhenParticipantNull() throws Exception {
        command.setStudy(new Study());
        replayMocks();
        assertNull(command.getAssignment());
        verifyMocks();
    }

    public void testCreatedOnDefaultsToNow() throws Exception {
        assertEquals(NOW, command.getAeReport().getCreatedAt());
    }

    public void testSetReportUpdatesOptionalReportDefMap() throws Exception {
        assertEquals("Test setup failure", 0, command.getOptionalReportDefinitionsMap().size());

        ReportDefinition r1 = createReportDefinition("R1");
        ReportDefinition r2 = createReportDefinition("R2");
        ReportDefinition r3 = createReportDefinition("R3");

        ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        aeReport.addReport(r1.createReport());
        aeReport.addReport(r2.createReport());
        aeReport.addReport(r3.createReport());

        aeReport.getReports().get(1).setRequired(true);
        command.setAeReport(aeReport);

        assertEquals(3, command.getOptionalReportDefinitionsMap().size());
        assertEquals(Boolean.TRUE, command.getOptionalReportDefinitionsMap().get(r1));
        assertEquals(Boolean.TRUE, command.getOptionalReportDefinitionsMap().get(r3));
    }

    public void testSetOptionalReportDefsUpdatesOptionalReportDefMap() throws Exception {
        assertEquals("Test setup failure", 0, command.getOptionalReportDefinitionsMap().size());

        ReportDefinition r1 = createReportDefinition("R1");
        ReportDefinition r2 = createReportDefinition("R2");
        ReportDefinition r3 = createReportDefinition("R3");

        command.setOptionalReportDefinitions(Arrays.asList(r1, r2, r3));
        assertEquals(3, command.getOptionalReportDefinitionsMap().size());
        assertEquals(Boolean.FALSE, command.getOptionalReportDefinitionsMap().get(r1));
        assertEquals(Boolean.FALSE, command.getOptionalReportDefinitionsMap().get(r2));
        assertEquals(Boolean.FALSE, command.getOptionalReportDefinitionsMap().get(r3));
    }

    public void testSetOptionalReportDefsClearExisting() throws Exception {
        assertEquals("Test setup failure", 0, command.getOptionalReportDefinitionsMap().size());

        ReportDefinition r1 = createReportDefinition("R1");
        ReportDefinition r2 = createReportDefinition("R2");
        ReportDefinition r3 = createReportDefinition("R3");
        ReportDefinition r4 = createReportDefinition("R4");

        command.getOptionalReportDefinitionsMap().put(r2, Boolean.TRUE);
        command.getOptionalReportDefinitionsMap().put(r4, Boolean.TRUE);

        command.setOptionalReportDefinitions(Arrays.asList(r1, r2, r3));

        assertEquals(4, command.getOptionalReportDefinitionsMap().size());
        assertEquals(Boolean.FALSE, command.getOptionalReportDefinitionsMap().get(r1));
        assertEquals("Reseted r2", Boolean.FALSE, command.getOptionalReportDefinitionsMap().get(r2));
        assertEquals(Boolean.FALSE, command.getOptionalReportDefinitionsMap().get(r3));
        assertEquals("Reseted r4", Boolean.TRUE, command.getOptionalReportDefinitionsMap().get(r4));
    }
    
    public void testGetSelectedReportDefs(){
    	assertEquals("Test setup failure", 0, command.getOptionalReportDefinitionsMap().size());
    	ReportDefinition r1 = createReportDefinition("R1");
        ReportDefinition r2 = createReportDefinition("R2");
        ReportDefinition r3 = createReportDefinition("R3");
        ReportDefinition r4 = createReportDefinition("R4");
       
        command.setSelectedReportDefinitions(Arrays.asList(r3, r4));
        command.setOptionalReportDefinitions(Arrays.asList(r1, r2, r4));
        
        List<ReportDefinition> list = command.getSelectedReportDefinitions();
        assertEquals(1, list.size());
    	
    }
    public void testGetInstantiatedReportDefs(){
    	ReportDefinition r1 = createReportDefinition("R1");
        ReportDefinition r2 = createReportDefinition("R2");
        ReportDefinition r3 = createReportDefinition("R3");
        ReportDefinition r4 = createReportDefinition("R4");
        
        ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        aeReport.addReport(r1.createReport());
        aeReport.addReport(r2.createReport());
        aeReport.addReport(r3.createReport());
        aeReport.getReports().get(0).setStatus(ReportStatus.WITHDRAWN);
        command.setAeReport(aeReport);
       
        List<ReportDefinition> list = command.getInstantiatedReportDefinitions();
        assertEquals(2, list.size());
    	
    }
    
}
