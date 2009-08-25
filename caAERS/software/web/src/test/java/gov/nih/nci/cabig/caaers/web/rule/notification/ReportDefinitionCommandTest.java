package gov.nih.nci.cabig.caaers.web.rule.notification;

import java.util.Arrays;
import java.util.Map;

import org.easymock.classextension.EasyMock;


import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepository;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportDefinitionCommandTest extends AbstractTestCase {
	
	ReportDefinitionCommand command;
	ConfigPropertyRepository cpRepository;
	ReportDefinition reportDefinition;
	ReportDefinitionDao reportDefinitionDao;
	
	ConfigProperty cp1;
	ConfigProperty cp2;
	ConfigProperty cp3;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		cp1 = Fixtures.createConfigProperty("cp1");
		cp1.setName("cp-name1");
		cp2 = Fixtures.createConfigProperty("cp2");
		cp2.setName("cp-name2");
		cp3 = Fixtures.createConfigProperty("cp3");
		cp3.setName("cp-name3");
		
		reportDefinition = Fixtures.createReportDefinition("test");
		
		cpRepository = registerMockFor(ConfigPropertyRepository.class);
		
		command = new ReportDefinitionCommand(reportDefinition, reportDefinitionDao, cpRepository);
		
	}

	public void testCollectRoleOptions() {
		EasyMock.expect(cpRepository.getByType(ConfigPropertyType.REPORT_ROLE_TYPE)).andReturn(Arrays.asList(cp1));
		EasyMock.expect(cpRepository.getByType(ConfigPropertyType.RESEARCH_STAFF_ROLE_TYPE)).andReturn(Arrays.asList(cp2));
		EasyMock.expect(cpRepository.getByType(ConfigPropertyType.INVESTIGATOR_ROLE_TYPE)).andReturn(Arrays.asList(cp3));
		
		replayMocks();
		Map<Object, Object> options = command.collectRecipientRoleOptions();
		
		assertEquals(4, options.size());
		assertEquals("Please select", options.get(""));
		assertEquals("cp-name1", options.get("cp1"));
		assertEquals("cp-name2", options.get("cp2"));
		assertEquals("cp-name3", options.get("cp3"));
		
		assertSame(options, command.collectRecipientRoleOptions());
		
		verifyMocks();
	}
	
	public void testCollectReportTypeOptions(){
		Map<Object, Object> options = command.collectReportTypeOptions();
		assertSame(options, command.collectReportTypeOptions());
		assertEquals(3, options.size());
		assertEquals("Please select", options.get(""));
	}
}
