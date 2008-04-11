package gov.nih.nci.cabig.caaers.rules.business.service;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class EvaluationServiceTest extends TestCase {

    AdverseEventEvaluationService adverseEventEvaluationService;

    ReportDefinitionDao reportDefinitionDao;

    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    ReportRepository reportRepository;

    OrganizationDao organizationDao;

    EvaluationServiceImpl service;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        

    }

    public void testFindRequiredReportDefinitions() throws Exception {
        String n1 = "24 Hr report";
        String n2 = "55 day report";

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
       

   
    }

    public void testIsSubmittable() throws Exception {
        ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        List<ExpeditedReportSection> mandatorySections = Arrays.asList(
                        ExpeditedReportSection.MEDICAL_INFO_SECTION,
                        ExpeditedReportSection.SURGERY_INTERVENTION_SECTION);
        ReportSubmittability messages = new ReportSubmittability();
        Report report = new Report();
        report.setAeReport(aeReport);
        aeReport.addReport(report);
        expect(adverseEventEvaluationService.mandatorySectionsForReport(report)).andReturn(
                        mandatorySections);
        expect(reportRepository.validate(report, mandatorySections)).andReturn(messages);

    }



}
