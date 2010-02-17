package gov.nih.nci.cabig.caaers.service.migrator;

import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/*
* @author Ion C. Olaru
* */
public class ReportDefinitionConverterTest extends CaaersDbTestCase{
	
	ReportDefinitionConverter reportDefinitionConverter;
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions reportDefinitions = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.reportdefinition");
		unmarshaller = jaxbContext.createUnmarshaller();
		reportDefinitionConverter = (ReportDefinitionConverter)getDeployedApplicationContext().getBean("reportDefinitionConverter");
	}
	
	public void testdtoToDomain() throws Exception {
		
		reportDefinitions = (gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions) unmarshaller.unmarshal(createInputStream("testdata/ctep_24_hour_sae_notification.xml"));
		gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitionType reportDefinitionDto = reportDefinitions.getReportDefinition().get(0);
		ReportDefinition reportDefinitionDomain = reportDefinitionConverter.dtoToDomain(reportDefinitionDto);
		
		assertNotNull(reportDefinitionDomain);
		assertEquals("TEST CTEP 24 Hour SAE Notification", reportDefinitionDomain.getName());
		assertNotNull(reportDefinitionDomain.getGroup());
		assertEquals("RT_INST", reportDefinitionDomain.getGroup().getCode());
		assertEquals(reportDefinitionDto.getDeliveryDefinition().size(), reportDefinitionDomain.getDeliveryDefinitions().size());
		assertEquals(reportDefinitionDto.getMandatoryField().size(), reportDefinitionDomain.getMandatoryFields().size());
		assertEquals(reportDefinitionDto.getPlannedNotificaiton().size(), reportDefinitionDomain.getPlannedNotifications().size());
		
		
	}
    
    /*
    * @author Ion C. Olaru
    * Testing whether the importing procedure is working for the reports definitions marked as Custom Report Format Type
    *
    * */
	public void testCustomDtoToDomain() throws Exception {
		reportDefinitions = (gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions) unmarshaller.unmarshal(createInputStream("testdata/custom-report.xml"));
		gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitionType reportDefinitionDto = reportDefinitions.getReportDefinition().get(0);
		ReportDefinition reportDefinitionDomain = reportDefinitionConverter.dtoToDomain(reportDefinitionDto);
		assertEquals(ReportFormatType.CUSTOM_REPORT, reportDefinitionDomain.getReportFormatType().getCode());
	}

	public void testdomainToDto() throws Exception {
		reportDefinitions = (gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions) unmarshaller.unmarshal(createInputStream("testdata/ctep_24_hour_sae_notification.xml"));
		gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitionType reportDefinitionDto = reportDefinitions.getReportDefinition().get(0);
		ReportDefinition reportDefinitionDomain = reportDefinitionConverter.dtoToDomain(reportDefinitionDto);
		assertNotNull(reportDefinitionDomain);
		assertEquals("TEST CTEP 24 Hour SAE Notification", reportDefinitionDomain.getName());
		assertNotNull(reportDefinitionDomain.getGroup());
		assertEquals("RT_INST", reportDefinitionDomain.getGroup().getCode());
		assertNotNull(reportDefinitionDomain.getGroup().getConfigType());
		assertEquals(ConfigPropertyType.REPORT_GROUP, reportDefinitionDomain.getGroup().getConfigType());
		
		reportDefinitions = reportDefinitionConverter.domainToDto(reportDefinitionDomain);
		reportDefinitionDto = reportDefinitions.getReportDefinition().get(0);
		
		assertNotNull(reportDefinitionDto);
		assertEquals("TEST CTEP 24 Hour SAE Notification", reportDefinitionDto.getName());
		assertEquals(reportDefinitionDomain.getDeliveryDefinitions().size(),reportDefinitionDto.getDeliveryDefinition().size());
		assertEquals(reportDefinitionDomain.getMandatoryFields().size(),reportDefinitionDto.getMandatoryField().size() );
		assertEquals(reportDefinitionDomain.getPlannedNotifications().size(),reportDefinitionDto.getPlannedNotificaiton().size());
		
	}
	
  private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
  InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
  return testDataStream;
}

}
