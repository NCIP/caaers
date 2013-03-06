/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web;


import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.acegisecurity.Authentication;
import org.easymock.classextension.EasyMock;
import org.springframework.web.servlet.ModelAndView;

public class TaskResolverControllerTest extends AbstractTestCase {
	
	private TaskResolverController taskResolverController;
	private CaaersSecurityFacade caaersSecurityFacade;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SecurityTestUtils.switchToSuperuser();
		taskResolverController = new TaskResolverController();
		caaersSecurityFacade = registerMockFor(CaaersSecurityFacade.class);
		taskResolverController.setCaaersSecurityFacade(caaersSecurityFacade);
	}
	
	public void testHandleRequestInternalNull() {
		taskResolverController.setUrls(new ArrayList<String>());
		assertNull(taskResolverController.handleRequestInternal(null, null));
		
		taskResolverController.setUrls(Arrays.asList(new String[]{"abc"}));
		taskResolverController.setObjectPrivilegeMap(new HashMap<String, String>());
		assertNull(taskResolverController.handleRequestInternal(null, null));
		
		taskResolverController.setObjectPrivilegeMap(new HashMap<String, String>() {{
			   put("abc", "pqr:xyz");
			}});
		EasyMock.expect(caaersSecurityFacade.checkAuthorization(EasyMock.isA(Authentication.class), EasyMock.isA(String.class), EasyMock.isA(String.class))).andReturn(false);
		replayMocks();
		assertNull(taskResolverController.handleRequestInternal(null, null));
		verifyMocks();
	}
	
	public void testHandleRequestInternal() {
		taskResolverController.setUrls(Arrays.asList(new String[]{"abc"}));
		taskResolverController.setObjectPrivilegeMap(new HashMap<String, String>() {{
			   put("abc", "pqr:xyz");
			}});
		EasyMock.expect(caaersSecurityFacade.checkAuthorization(EasyMock.isA(Authentication.class), EasyMock.isA(String.class), EasyMock.isA(String.class))).andReturn(true);
		replayMocks();
		ModelAndView mv = taskResolverController.handleRequestInternal(null, null);
		assertEquals("redirect:abc", mv.getViewName());
		verifyMocks();
	}

}
