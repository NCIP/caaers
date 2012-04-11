package gov.nih.nci.cabig.caaers.service.migrator;

import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/*
* @author Ion C. Olaru
* @author Biju Joseph
* */
public class ReportDefinitionConverterTest extends CaaersDbTestCase{
	
	ReportDefinitionConverter reportDefinitionConverter;
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitions reportDefinitions = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.reportdefinition");
		unmarshaller = jaxbContext.createUnmarshaller();
		reportDefinitionConverter = (ReportDefinitionConverter)getDeployedApplicationContext().getBean("reportDefinitionConverter");
	}
	
	public void testdtoToDomain() throws Exception {
		
		reportDefinitions = (gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitions) unmarshaller.unmarshal(createInputStream("testdata/ctep_24_hour_sae_notification.xml"));
        gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitionType reportDefinitionDto = reportDefinitions.getReportDefinition().get(0);
		ReportDefinition reportDefinitionDomain = reportDefinitionConverter.dtoToDomain(reportDefinitionDto);
		
		assertNotNull(reportDefinitionDomain);
		assertEquals("TEST CTEP 24 Hour SAE Notification", reportDefinitionDomain.getName());
		assertNotNull(reportDefinitionDomain.getGroup());
		assertEquals("RT_INST", reportDefinitionDomain.getGroup().getCode());
		assertEquals(reportDefinitionDto.getDeliveryDefinition().size(), reportDefinitionDomain.getDeliveryDefinitions().size());
		assertEquals(reportDefinitionDto.getMandatoryField().size(), reportDefinitionDomain.getMandatoryFields().size());
		assertEquals(reportDefinitionDto.getPlannedNotificaiton().size(), reportDefinitionDomain.getPlannedNotifications().size());
        int s = reportDefinitionDomain.getMandatoryFields().size();

        assertFalse(reportDefinitionDto.getMandatoryField().get(0).isSelfReferenced());
        assertFalse(reportDefinitionDomain.getMandatoryFields().get(0).isSelfReferenced());

        assertTrue(reportDefinitionDto.getMandatoryField().get(s -1).isSelfReferenced());
        assertTrue(reportDefinitionDomain.getMandatoryFields().get(s -1).isSelfReferenced());
        assertEquals("diseaseHistory.diagnosisDate", reportDefinitionDto.getMandatoryField().get(s -1).getFieldPath());
        assertEquals("diseaseHistory.diagnosisDate", reportDefinitionDomain.getMandatoryFields().get(s -1).getFieldPath());
        assertEquals("Rule-1", reportDefinitionDto.getMandatoryField().get(s -1).getRuleName());
        assertEquals("Rule-1", reportDefinitionDomain.getMandatoryFields().get(s -1).getRuleName());

		
	}
    
    /*
    * @author Ion C. Olaru
    * Testing whether the importing procedure is working for the reports definitions marked as Custom Report Format Type
    *
    * */
	public void testCustomDtoToDomain() throws Exception {
		reportDefinitions = (gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitions) unmarshaller.unmarshal(createInputStream("testdata/custom-report.xml"));
        gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitionType reportDefinitionDto = reportDefinitions.getReportDefinition().get(0);
		ReportDefinition reportDefinitionDomain = reportDefinitionConverter.dtoToDomain(reportDefinitionDto);
		assertEquals(ReportFormatType.CUSTOM_REPORT, reportDefinitionDomain.getReportFormatType());
        assertNotNull(reportDefinitionDomain.findReportMandatoryFieldDefinitionByPath("diseaseHistory.diagnosisDate"));
        assertEquals(RequirednessIndicator.RULE, reportDefinitionDomain.findReportMandatoryFieldDefinitionByPath("diseaseHistory.diagnosisDate").getMandatory());
        assertEquals("testurl", reportDefinitionDomain.findReportMandatoryFieldDefinitionByPath("diseaseHistory.diagnosisDate").getRuleBindURL());
        assertEquals("Hello,Tester", reportDefinitionDomain.findReportMandatoryFieldDefinitionByPath("diseaseHistory.diagnosisDate").getRuleName());
        assertNotNull(reportDefinitionDomain.findReportMandatoryFieldDefinitionByPath("diseaseHistory.ctepStudyDisease"));
        assertNull(reportDefinitionDomain.findReportMandatoryFieldDefinitionByPath("diseaseHistory.ctepStudyDisease").getRuleBindURL());
        assertNull(reportDefinitionDomain.findReportMandatoryFieldDefinitionByPath("diseaseHistory.ctepStudyDisease").getRuleName());
        assertEquals(RequirednessIndicator.MANDATORY, reportDefinitionDomain.findReportMandatoryFieldDefinitionByPath("diseaseHistory.ctepStudyDisease").getMandatory());
	}

	public void testdomainToDto() throws Exception {
		reportDefinitions = (gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitions) unmarshaller.unmarshal(createInputStream("testdata/ctep_24_hour_sae_notification.xml"));
        gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitionType reportDefinitionDto = reportDefinitions.getReportDefinition().get(0);
		ReportDefinition reportDefinitionDomain = reportDefinitionConverter.dtoToDomain(reportDefinitionDto);
		assertNotNull(reportDefinitionDomain);
		assertEquals("TEST CTEP 24 Hour SAE Notification", reportDefinitionDomain.getName());
		assertNotNull(reportDefinitionDomain.getGroup());
		assertEquals("RT_INST", reportDefinitionDomain.getGroup().getCode());
		assertNotNull(reportDefinitionDomain.getGroup().getConfigType());
		assertEquals(ConfigPropertyType.REPORT_GROUP, reportDefinitionDomain.getGroup().getConfigType());

        //add mandatory fields
        ReportMandatoryFieldDefinition rmfd = Fixtures.createMandatoryField("abcd.efg", RequirednessIndicator.RULE);
        rmfd.setRuleName("xxx");
        rmfd.setRuleBindURL("xxx");
        rmfd.setSelfReferenced(true);
        reportDefinitionDomain.addReportMandatoryFieldDefinition(rmfd);
		
		reportDefinitions = reportDefinitionConverter.domainToDto(reportDefinitionDomain);
		reportDefinitionDto = reportDefinitions.getReportDefinition().get(0);
		
		assertNotNull(reportDefinitionDto);
		assertEquals("TEST CTEP 24 Hour SAE Notification", reportDefinitionDto.getName());
		assertEquals(reportDefinitionDomain.getDeliveryDefinitions().size(),reportDefinitionDto.getDeliveryDefinition().size());
		assertEquals(reportDefinitionDomain.getMandatoryFields().size(),reportDefinitionDto.getMandatoryField().size() );
		assertEquals(reportDefinitionDomain.getPlannedNotifications().size(),reportDefinitionDto.getPlannedNotificaiton().size());

        int s = reportDefinitionDomain.getMandatoryFields().size();
        assertTrue(reportDefinitionDomain.getMandatoryFields().get(s-1).isRuleBased());
        assertTrue(reportDefinitionDto.getMandatoryField().get(s-1).isSelfReferenced());


		
	}

	
  private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
  InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
  return testDataStream;
}

}
