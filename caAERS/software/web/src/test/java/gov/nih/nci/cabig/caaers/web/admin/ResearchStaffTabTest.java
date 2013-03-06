/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
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
 * @author Ram Seethiraju
 *
 */
public class ResearchStaffTabTest extends WebTestCase {
	ResearchStaffTab tab;
	
	ResearchStaffRepository repository;
	CSMUserRepository csmUserRepository;
	
	ResearchStaffCommand command;
	ResearchStaff staff;
	
	Errors errors;
	
	BeanWrapper commandWrapper;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		tab = new  ResearchStaffTab();
		
		repository = registerMockFor(ResearchStaffRepository.class);
		csmUserRepository = registerMockFor(CSMUserRepository.class);
		
		command = new ResearchStaffCommand();
		staff = new LocalResearchStaff();
		command.setResearchStaff(staff);
		errors = new BindException(command, "command");
		commandWrapper = new BeanWrapperImpl(command);
		
		tab.setResearchStaffRepository(repository);
		tab.setCsmUserRepository(csmUserRepository);
		
	}

	public void testValidateResearchStaffCommandBeanWrapperMapOfStringInputFieldGroupErrors() {
		staff.setEmailAddress("hello@ab.com");
		staff.setLoginId("abcd");
		
		EasyMock.expect(repository.searchResearchStaff((ResearchStaffQuery) EasyMock.anyObject())).andReturn(new ArrayList<ResearchStaff>());
		EasyMock.expect(csmUserRepository.loginIDInUse("abcd")).andReturn(false).anyTimes();
        EasyMock.expect(csmUserRepository.getUserByName("abcd")).andReturn(null).anyTimes();
		replayMocks();
		tab.validate(command, commandWrapper, tab.createFieldGroups(command), errors);
		assertEquals(1, errors.getGlobalErrorCount());
		verifyMocks();
	}
	
	

	public void testValidate() {
		staff.setEmailAddress("hello@ab.com");
		staff.setLoginId("abcd");
		
		SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
		siteResearchStaff.setOrganization(Fixtures.createOrganization("test", "test"));
		staff.addSiteResearchStaff(siteResearchStaff);

		EasyMock.expect(repository.searchResearchStaff((ResearchStaffQuery) EasyMock.anyObject())).andReturn(new ArrayList<ResearchStaff>());
		EasyMock.expect(csmUserRepository.loginIDInUse("abcd")).andReturn(false).anyTimes();
		EasyMock.expect(csmUserRepository.getUserByName("abcd")).andReturn(null).anyTimes();

		replayMocks();
		tab.validate(command, commandWrapper, tab.createFieldGroups(command), errors);
		System.out.println(errors);
		verifyMocks();
	}
}
