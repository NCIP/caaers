package gov.nih.nci.cabig.caaers.rules.business.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static org.easymock.EasyMock.*;
import org.hibernate.jdbc.Expectation;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationService;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationServiceImpl;
import gov.nih.nci.cabig.caaers.rules.business.service.EvaluationServiceImpl;
import gov.nih.nci.cabig.caaers.service.ErrorMessages;
import gov.nih.nci.cabig.caaers.service.ReportService;

public class EvaluationServiceTest extends CaaersTestCase {

	AdverseEventEvaluationService adverseEventEvaluationService;
    ReportDefinitionDao reportDefinitionDao;
    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    ReportService reportService;
    OrganizationDao  organizationDao;

    EvaluationServiceImpl service;

    @Override
	protected void setUp() throws Exception {
		super.setUp();
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		expeditedAdverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		organizationDao = registerDaoMockFor(OrganizationDao.class);

		adverseEventEvaluationService = registerMockFor(AdverseEventEvaluationService.class);
		reportService = registerMockFor(ReportService.class);

		service =  new EvaluationServiceImpl();

		service.setExpeditedAdverseEventReportDao(expeditedAdverseEventReportDao);
		service.setOrganizationDao(organizationDao);
		service.setReportDefinitionDao(reportDefinitionDao);

		service.setReportService(reportService);
		service.setAdverseEventEvaluationService(adverseEventEvaluationService);

	}

    public void testFindRequiredReportDefinitions() throws Exception {
    	String n1 = "24 Hr report";
    	String n2 =  "55 day report";

    	ReportDefinition rd1 = new ReportDefinition();
    	rd1.setName(n1);

    	ReportDefinition rd2 = new ReportDefinition();
    	rd2.setName(n2);

    	List<String> reportNames = new ArrayList<String>();
    	reportNames.add(n1);
    	reportNames.add(n2);
    	Map<String, List<String>> map = new HashMap<String, List<String>>();
    	map.put("junk", reportNames);

    	ExpeditedAdverseEventReport aereport = new ExpeditedAdverseEventReport();
    	expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aereport)).andReturn(map);

    	expect(reportDefinitionDao.getByName(n1)).andReturn(rd1);
    	expect(reportDefinitionDao.getByName(n2)).andReturn(rd2);
    	replayMocks();
    	List<ReportDefinition> actualDefList = service.findRequiredReportDefinitions(aereport);
    	verifyMocks();

    	assertEquals("incorrect number of report definitions", 2, actualDefList.size());
    	assertEquals("report definition name is incorrect" , n1, actualDefList.get(0).getName());



    }

    public void testIsSubmittable() throws Exception{
    	ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
    	List<String> mandatorySections = Arrays.asList("MEDICAL_INFO_SCECTION","SURGERY_INTERVENTION_SECTION");
    	ErrorMessages messages = new ErrorMessages();
    	Report report = new Report();
    	report.setAeReport(aeReport);
    	aeReport.addReport(report);
    	expect(adverseEventEvaluationService.mandatorySectionsForReport(report)).andReturn(mandatorySections);
    	expect(reportService.validate(report, mandatorySections)).andReturn(messages);
    	replayMocks();
    	ErrorMessages msgs = service.isSubmitable(report);
    	verifyMocks();
    	assertEquals("ErrorMessage object is not same",messages, msgs);
    }

}
