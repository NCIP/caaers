/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;


import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.easymock.classextension.EasyMock;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class UserTabTest extends AbstractTestCase {

	private UserTab userTab;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MessageSource messageSource;
	private UserCommand command;
	private Errors errors;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		userTab = new UserTab();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		messageSource = registerMockFor(MessageSource.class);
		userTab.setMessageSource(messageSource);
		command = registerMockFor(UserCommand.class);
		errors = registerMockFor(Errors.class);
	}
	
	public void testGetAJAXMethodInvAttrName() {
		assertEquals("_asynchronous", userTab.getAJAXMethodInvAttrName());
	}

	
	public void testGetMethodName() {
		assertNull(userTab.getMethodName(request));
		request.setParameter("currentItem", "Person");
		request.setParameter("task", "addsite");
		assertEquals("addSitePerson", userTab.getMethodName(request));
		request.removeParameter("task");
		request.setParameter("task", "removesite");
		assertEquals("removeSitePerson", userTab.getMethodName(request));
	}

	
	public void testCreateFieldGroupsUserCommand() {
		assertEquals(0, userTab.createFieldGroups(registerMockFor(UserCommand.class)).size());
	}

	
	public void testReferenceDataHttpServletRequestUserCommand() {
		EasyMock.expect(messageSource.getMessage(
				EasyMock.isA(String.class), EasyMock.isA(Object[].class), (String)EasyMock.isNull(), EasyMock.isA(Locale.class))).andReturn("something").times(2);
		replayMocks();
		assertEquals(1, userTab.referenceData(request, command).size());
		request.setParameter("created", "something");
		assertEquals(1, userTab.referenceData(request, command).size());
		request.removeParameter("created");
		request.setParameter("created", "OK");
		assertEquals(2, userTab.referenceData(request, command).size());
		request.removeParameter("created");
		request.setParameter("edited", "something");
		assertEquals(1, userTab.referenceData(request, command).size());
		request.removeParameter("edited");
		request.setParameter("edited", "OK");
		assertEquals(2, userTab.referenceData(request, command).size());
		verifyMocks();
	}

	
	public void testAddSitePerson() {
		command.addSitePersonnel(EasyMock.isA(SitePerson.class));
		EasyMock.expect(command.getSitePersonnel()).andReturn(new ArrayList<SitePerson>(){{add(new SitePerson());}});
		replayMocks();
		ModelAndView mv = userTab.addSitePerson(request, command, errors);
		assertNotNull(mv.getModel());
		assertEquals(new Integer(0), ((Integer[])(mv.getModel().get("indexes")))[0]);
		verifyMocks();
	}
	

	public void testRemoveSitePerson() {
		request.setParameter("index", 0+"");
		List<SitePerson> sitePeople = new ArrayList<SitePerson>(){{add(new SitePerson());}};
		EasyMock.expect(command.getSitePersonnel()).andReturn(sitePeople).times(2);
		replayMocks();
		ModelAndView mv = userTab.removeSitePerson(request, command, errors);
		assertNotNull(mv);
		assertEquals(0, ((Integer[])mv.getModel().get("indexes")).length);
		verifyMocks();
	}

	
	public void testValidateUserCommandBeanWrapperMapOfStringInputFieldGroupErrors() {
	}

}
