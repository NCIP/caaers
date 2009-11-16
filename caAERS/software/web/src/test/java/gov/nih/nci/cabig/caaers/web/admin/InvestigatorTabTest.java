package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.ArrayList;

import org.easymock.classextension.EasyMock;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
/**
 * 
 * @author Biju Joseph
 *
 */
public class InvestigatorTabTest extends WebTestCase {

	 ConfigProperty configurationProperty;
	 Configuration configuration;
	 OrganizationDao organizationDao;
	 CSMUserRepository csmUserRepository;
	 InvestigatorRepository investigatorRepository;
	 
	 
	 Errors errors;
	 BeanWrapper commandWrapper;
	 
	 Investigator command;
	 InvestigatorTab tab ;
	 
	protected void setUp() throws Exception {
		super.setUp();
		
		csmUserRepository = registerMockFor(CSMUserRepository.class);
		configurationProperty = registerMockFor(ConfigProperty.class);
		configuration = registerMockFor(Configuration.class);
		organizationDao = registerDaoMockFor(OrganizationDao.class);
		investigatorRepository = registerMockFor(InvestigatorRepository.class);
		
		command = new LocalInvestigator();
		errors = new BindException(command, "command");
		commandWrapper = new BeanWrapperImpl(command);

		tab = new InvestigatorTab();
		tab.setConfiguration(configuration);
		tab.setConfigurationProperty(configurationProperty);
		tab.setCsmUserRepository(csmUserRepository);
		tab.setInvestigatorRepository(investigatorRepository);
		tab.setOrganizationDao(organizationDao);
		
	}

	public void testValidate() {
		command.setEmailAddress("hello@ab.com");
		command.setLoginId("abcd");
		
		EasyMock.expect(csmUserRepository.loginIDInUse("abcd")).andReturn(false).anyTimes();
		EasyMock.expect(investigatorRepository.searchInvestigator((InvestigatorQuery) EasyMock.anyObject())).andReturn(new ArrayList<Investigator>());
		EasyMock.expect(configuration.getAuthenticationMode()).andReturn("abcd");
		
		replayMocks();
		tab.validate(command, commandWrapper, tab.createFieldGroups(command), errors);
		assertFalse(errors.hasErrors());
		verifyMocks();
	}


	

}
