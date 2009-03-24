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
        tab.setConfigurationProperty(configurationProperty);
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
    		reportDefinition.setAmendable(false);
    		reportDefinition.setExpedited(false);
    		reportDefinition.setName("repDefn " + i);
    		reportDefinition.setOrganization(Fixtures.createOrganization("test org"));
    		reportDefinition.getOrganization().setNciInstituteCode("test nci code");
    		report.setReportDefinition(reportDefinition);
    		ReportVersion reportVersion= new ReportVersion();
    		reportVersion.setReportStatus(ReportStatus.PENDING);
    		report.addReportVersion(reportVersion);
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
    	rd.setExpedited(true);
    	rd.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd.setDuration(5);
    	rd.setId(10);
    	command.getSelectedReportDefinitions().add(rd);
    }
    
    /**
     * This method tests the postProcess method when the user has chosen the "createNew" option in the review-report page.
     * @throws Exception
     */
    public void testPostProcess() throws Exception{
    	request.getSession().setAttribute("action", "createNew");
    	additionalSetupOnCommand();
    	setupSelectedReportDefintiions();
    	command.setWorkflowEnabled(false);
    	expect(command.getEvaluationService().addOptionalReports(command.getAeReport(), command.getReportDefinitionListForCreation(), false)).andReturn(null);
    	expeditedReportDao.save(command.getAeReport());
    	expect(command.getEvaluationService().mandatorySections(command.getAeReport())).andReturn(null);
    	replayMocks();
    	((ReporterTab)tab).postProcess(request, command, errors);
    	verifyMocks();
    	assertEquals("ReportDefinitionListForCreation is set incorrectly", 1, command.getReportDefinitionListForCreation().size());
    }
    
    /**
     * This method tests the onDisplay method when the user has chosen the "edit" option in the review-report page and not selected any new reports.
     * @throws Exception
     */
    public void testOnDisplayEditNoNewReports() throws Exception{
    	addReportsToAeReport();
    	additionalSetupOnCommand();
    	request.getSession().setAttribute("action", "editReport");
    	expeditedReportDao.save(command.getAeReport());
    	expect(command.getEvaluationService().mandatorySections(command.getAeReport())).andReturn(null);
    	replayMocks();
    	((ReporterTab)tab).onDisplay(request, command);
    	verifyMocks();
    }
    
    /**
     * This method tests the onDisplay method when the user has chosen the "edit" option in the review-report page and selected new reports whose due date is
     * earlier than that of the existing report.
     * @throws Exception
     */
    public void testOnDisplayEditNewReportsEarlier() throws Exception{
    	addReportsToAeReport();
    	additionalSetupOnCommand();
    	
    	// Set the due dates on the existing reports and modify the amendable/expedited properties
    	command.getAeReport().getReports().get(0).getReportDefinition().setAmendable(true);
    	command.getAeReport().getReports().get(0).getReportDefinition().setExpedited(true);
    	command.getAeReport().getReports().get(1).getReportDefinition().setName("test 24 hr notification");
    	//command.getAeReport().getReports().get(1).getReportDefinition().setId(10);
    	command.getAeReport().getReports().get(0).getReportDefinition().setName("test existing amendable report");
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar now = GregorianCalendar.getInstance();
    	now.add(Calendar.DAY_OF_MONTH, 7);
    	String dateString = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);
    	command.getAeReport().getReports().get(0).setDueOn(formatter.parse(dateString));
    	command.getAeReport().getReports().get(1).setDueOn(formatter.parse(dateString));
    	
    	// Setup the newly Selected ReportDefinition
    	setupSelectedReportDefintiions();
    	request.getSession().setAttribute("action", "editReport");

    	// Setup the expect calls
    	expect(command.getEvaluationService().addOptionalReports(command.getAeReport(), command.getReportDefinitionListForCreation(), false)).andReturn(null);
    	reportRepository.replaceReport(command.getAeReport().getReports().get(0));
    	expeditedReportDao.save(command.getAeReport());
    	expect(command.getEvaluationService().mandatorySections(command.getAeReport())).andReturn(null);
    	
    	replayMocks();
    	// actual call to the method being tested.
    	((ReporterTab)tab).onDisplay(request, command);
    	verifyMocks();
    	
    	assertEquals("Incorrect number of reports for creation", 1, command.getReportDefinitionListForCreation().size());
    	assertEquals("Incorrect number of reports for withdrawal", 1, command.getReportListForWithdrawal().size());
    	assertEquals("Incorrect number of reports for amendment", 0, command.getReportListForAmendment().size());
    }
    
    /**
     * This method tests the onDisplay method when the user has chosen the "edit" option in the review-report page and selected new reports whose due dates is
     * later than that of the existing reports.
     * @throws Exception
     */
    public void testOnDisplayEditNewReportsLater() throws Exception{
    	addReportsToAeReport();
    	additionalSetupOnCommand();
    	
    	// Set the due dates on the existing reports and modify the amendable/expedited properties
    	command.getAeReport().getReports().get(0).getReportDefinition().setAmendable(true);
    	command.getAeReport().getReports().get(0).getReportDefinition().setExpedited(true);
    	command.getAeReport().getReports().get(1).getReportDefinition().setName("test 24 hr notification");
    	command.getAeReport().getReports().get(0).getReportDefinition().setId(10);
    	command.getAeReport().getReports().get(0).getReportDefinition().setName("test existing amendable report");
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar now = GregorianCalendar.getInstance();
    	now.add(Calendar.DAY_OF_MONTH, 3);
    	String dateString = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);
    	command.getAeReport().getReports().get(0).setDueOn(formatter.parse(dateString));
    	command.getAeReport().getReports().get(1).setDueOn(formatter.parse(dateString));
    	
    	// Setup the newly Selected ReportDefinition
    	setupSelectedReportDefintiions();
    	request.getSession().setAttribute("action", "editReport");

    	// Setup the expect calls
    	//expect(command.getEvaluationService().addOptionalReports(command.getAeReport(), command.getReportDefinitionListForCreation(), false)).andReturn(null);
    	//reportRepository.replaceReport(command.getAeReport().getReports().get(0));
    	
    	expeditedReportDao.save(command.getAeReport());
    	expect(command.getEvaluationService().mandatorySections(command.getAeReport())).andReturn(null);
    	
    	replayMocks();
    	// actual call to the method being tested.
    	((ReporterTab)tab).onDisplay(request, command);
    	verifyMocks();
    	
    	assertEquals("Incorrect number of reports for creation", 0, command.getReportDefinitionListForCreation().size());
    	assertEquals("Incorrect number of reports for withdrawal", 0, command.getReportListForWithdrawal().size());
    	assertEquals("Incorrect number of reports for amendment", 0, command.getReportListForAmendment().size());
    }
    
    /**
     * This method tests the onDisplay method when the user has chosen the "amend" option in the review-report page and not selected any new reports.
     * @throws Exception
     */
    public void testOnDisplayAmendNoNewReports() throws Exception{
    	addReportsToAeReport();
    	additionalSetupOnCommand();
    	request.getSession().setAttribute("action", "amendReport");
    	expeditedReportDao.save(command.getAeReport());
    	expect(command.getEvaluationService().mandatorySections(command.getAeReport())).andReturn(null);
    	replayMocks();
    	((ReporterTab)tab).onDisplay(request, command);
    	verifyMocks();
    }
    
    /**
     * This method tests the onDisplay method when the user has chosen the "amend" option in the review-report page and selected new reports which are similar
     * to the existing ones. Example, if the aeReport has a 10day report and the user tries to amend it with a 10day report.
     * @throws Exception
     */
    public void testOnDisplayAmendDifferentNewReports() throws Exception{
    	addReportsToAeReport();
    	additionalSetupOnCommand();
    	
    	// Set the due dates on the existing reports and modify the amendable/expedited properties
    	command.getAeReport().getReports().get(0).getReportDefinition().setAmendable(true);
    	command.getAeReport().getReports().get(0).getReportDefinition().setExpedited(true);
    	command.getAeReport().getReports().get(1).getReportDefinition().setName("test 24 hr notification");
    	command.getAeReport().getReports().get(0).getReportDefinition().setId(11);
    	
    	command.getAeReport().getReports().get(0).setStatus(ReportStatus.COMPLETED);
    	command.getAeReport().getReports().get(0).getLastVersion().setSubmittedOn(new Date());
    	command.getAeReport().getReports().get(0).getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	command.getAeReport().getReports().get(1).setStatus(ReportStatus.COMPLETED);
    	command.getAeReport().getReports().get(1).getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	command.getAeReport().getReports().get(1).getLastVersion().setSubmittedOn(new Date());
    	
    	// Setup the newly Selected ReportDefinition
    	setupSelectedReportDefintiions();
    	request.getSession().setAttribute("action", "amendReport");

    	// Setup the expect calls
    	expect(command.getEvaluationService().addOptionalReports(command.getAeReport(), command.getReportDefinitionListForCreation(), true)).andReturn(null);
    	reportRepository.replaceReport(command.getAeReport().getReports().get(0));
    	expeditedReportDao.save(command.getAeReport());
    	expect(command.getEvaluationService().mandatorySections(command.getAeReport())).andReturn(null);
    	
    	replayMocks();
    	// actual call to the method being tested.
    	((ReporterTab)tab).onDisplay(request, command);
    	verifyMocks();
    	
    	assertEquals("Incorrect number of reports for creation", 1, command.getReportDefinitionListForCreation().size());
    	assertEquals("Incorrect number of reports for withdrawal", 1, command.getReportListForWithdrawal().size());
    	assertEquals("Incorrect number of reports for amendment", 0, command.getReportListForAmendment().size());
    }
}
