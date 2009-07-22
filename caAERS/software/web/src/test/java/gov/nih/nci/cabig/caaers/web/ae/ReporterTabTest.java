package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 * @author Sameer Sawant
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ReporterTabTest extends AeTabTestCase {
    private EvaluationService evaluationService;
    private ConfigProperty configurationProperty;

    private AdverseEvent ae0;

    @Override
    protected void setUp() throws Exception {
        evaluationService = registerMockFor(EvaluationService.class);
        configurationProperty = registerMockFor(ConfigProperty.class);
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("titleType", new ArrayList<Lov>());
        expect(configurationProperty.getMap()).andReturn(map).anyTimes();
     
        super.setUp();
        ae0 = command.getAeReport().getAdverseEvents().get(0);
        assertEquals("Test setup failure -- only expected 1 AE initially", 1, command.getAeReport().getAdverseEvents().size());
    }

    @Override
    protected AeTab createTab() {
        ReporterTab tab = new ReporterTab();
        tab.setEvaluationService(evaluationService);
        return tab;
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().getReporter().getContactMechanisms().put("phone", "foo");
        cmd.getAeReport().getReporter().getContactMechanisms().put("fax", "foo");
        cmd.getAeReport().getPhysician().getContactMechanisms().put("phone", "foo");
        cmd.getAeReport().getPhysician().getContactMechanisms().put("fax", "foo");
    }

    public void testGroupsIncludesReporter() throws Exception {
    	replayMocks();
        InputFieldGroup actual = getTab().createFieldGroups(command).get("reporter");
        assertNotNull("No reporter group", actual);
        assertEquals("Wrong group name", "reporter", actual.getName());
        assertEquals("Wrong display name", "Reporter details", actual.getDisplayName());
    }

    public void testGroupsIncludesPhysician() throws Exception {
    	replayMocks();
        InputFieldGroup actual = getTab().createFieldGroups(command).get("physician");
        assertNotNull("No physician group", actual);
        assertEquals("Wrong group name", "physician", actual.getName());
        assertEquals("Wrong display name", "Physician details", actual.getDisplayName());
    }

    public void testReporterFieldProperties() throws Exception {
    	replayMocks();
        assertFieldProperties("reporter","aeReport.reporter.title",
        				"aeReport.reporter.firstName",
                        "aeReport.reporter.middleName", "aeReport.reporter.lastName",
                        "aeReport.reporter.contactMechanisms[e-mail]",
                        "aeReport.reporter.contactMechanisms[phone]",
                        "aeReport.reporter.contactMechanisms[fax]",
                        "aeReport.reporter.address.street",
                        "aeReport.reporter.address.city",
                        "aeReport.reporter.address.state",
                        "aeReport.reporter.address.zip");
    }

    public void testPhysicianFieldProperties() throws Exception {
    	replayMocks();
        assertFieldProperties("physician", "aeReport.physician.title","aeReport.physician.firstName",
                        "aeReport.physician.middleName", "aeReport.physician.lastName",
                        "aeReport.physician.contactMechanisms[e-mail]",
                        "aeReport.physician.contactMechanisms[phone]",
                        "aeReport.physician.contactMechanisms[fax]",
                        "aeReport.physician.address.street",
                        "aeReport.physician.address.city",
                        "aeReport.physician.address.state",
                        "aeReport.physician.address.zip");
    }

    public void testReporterFirstNameRequired() throws Exception {
        command.getAeReport().getReporter().setFirstName(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.reporter.firstName", "First name");
    }

    public void testReporterLastNameRequired() throws Exception {
        command.getAeReport().getReporter().setLastName(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.reporter.lastName", "Last name");
    }

    public void testReporterEmailAddressRequired() throws Exception {
        command.getAeReport().getReporter().getContactMechanisms().remove(ReportPerson.EMAIL);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.reporter.contactMechanisms[e-mail]",
                        "E-mail address");
    }

    public void testPhysicianFirstNameRequired() throws Exception {
        command.getAeReport().getPhysician().setFirstName(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.physician.firstName", "First name");
    }

    public void testPhysicianLastNameRequired() throws Exception {
        command.getAeReport().getPhysician().setLastName(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.physician.lastName", "Last name");
    }

    public void testPhysicianEmailAddressRequired() throws Exception {
        command.getAeReport().getPhysician().getContactMechanisms().remove(ReportPerson.EMAIL);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.physician.contactMechanisms[e-mail]",
                        "E-mail address");
    }

    public void testValidate(){
    	command.setWorkflowEnabled(true);
    	replayMocks();
    	BeanWrapper commandBean = new BeanWrapperImpl(command);
    	InputFieldGroupMap fieldGroups = getTab().createFieldGroups(command);
    	getTab().validate(command, commandBean, fieldGroups, errors);
    	assertTrue(errors.hasErrors());
    	assertEquals("SAE_019", errors.getFieldError("aeReport.reporter.user").getCode());
    	verifyMocks();
    }
    
    private AdverseEvent addAEToCommand() {
        AdverseEvent ae = new AdverseEvent();
        command.getAeReport().addAdverseEvent(ae);
        return ae;
    }
    
    public void addReportsToAeReport(){
    	for(int i = 0; i < 2; i++){
    		Report report = new Report();
    		report.setId(i);
    		ReportDefinition reportDefinition = new ReportDefinition();
    		reportDefinition.setId(i+1);
    		reportDefinition.setAmendable(false);
    		reportDefinition.setGroup(Fixtures.createConfigProperty("not expedited"));
    		reportDefinition.setName("repDefn " + i);
    		reportDefinition.setOrganization(Fixtures.createOrganization("test org"));
    		reportDefinition.getOrganization().setNciInstituteCode("test nci code");
    		report.setReportDefinition(reportDefinition);
    		ReportVersion reportVersion= report.getLastVersion();
    		reportVersion.setReportStatus(ReportStatus.PENDING);
    		command.getAeReport().addReport(report);
    	}
    }
    
    public void additionalSetupOnCommand(){
    	command.getAeReport().setAssignment(Fixtures.createAssignment());
    	command.getAeReport().getStudy().setPrimaryFundingSponsorOrganization(Fixtures.createOrganization("test org"));
    	command.getAeReport().getStudy().getPrimaryFundingSponsorOrganization().setNciInstituteCode("test nci code");
    	command.getAeReport().setId(1);
    }
    
    public void setupSelectedReportDefintiions(){
    	ReportDefinition rd = Fixtures.createReportDefinition("test 5 day report", "test nci code");
    	rd.setAmendable(true);
    	rd.setGroup(Fixtures.createConfigProperty("RT_EXPEDITED"));
    	rd.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd.setDuration(5);
    	rd.setId(10);
    	command.getSelectedReportDefinitions().add(rd);
    }
   
}
