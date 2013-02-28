/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.tools.spring.tabbedflow;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class ReflexiveAjaxableTabTest extends AbstractTestCase {

	private ReflexiveAjaxableTab reflexiveAjaxableTab;
	private MockHttpServletRequest request;
	
	@Override
	protected void setUp() throws Exception {
//		reflexiveAjaxableTab = new ReflexiveAjaxableTab("1", "2", "3", new Class[]{HttpServletRequest.class}) {
//		reflexiveAjaxableTab = new ReflexiveAjaxableTab("1", "2", "3") {
//			public ModelAndView test(HttpServletRequest request, Object command, Errors errors){
//				ModelAndView mv = new ModelAndView("some_name");
//				mv.getModel().put("ajax", "test");
//				return mv;
//			}
//		};
		reflexiveAjaxableTab = new TestReflexiveTab("1", "2", "3");
		request = new MockHttpServletRequest();
	}
	
	public void testPostProcessAsynchronous() throws Exception{
		request.addParameter("_asyncMethodName", "test");
		ModelAndView mv = reflexiveAjaxableTab.postProcessAsynchronous(request, new Object(), registerMockFor(Errors.class));
		assertEquals("test", mv.getModel().get("ajax"));
	}

	public void testGetAJAXMethodInvAttrName() {
		assertEquals("_asyncMethodName", reflexiveAjaxableTab.getAJAXMethodInvAttrName());
	}

	public void testGetMethodName() {
		request.addParameter("_asyncMethodName", "test");
		assertEquals("test", reflexiveAjaxableTab.getMethodName(request));
	}
	
	public void testMethodInvocationRequest() {
		assertFalse(reflexiveAjaxableTab.methodInvocationRequest(request));
		request.addParameter("_asyncMethodName", "test");
		assertTrue(reflexiveAjaxableTab.methodInvocationRequest(request));
	}

	public class TestReflexiveTab extends ReflexiveAjaxableTab{
		
		public TestReflexiveTab(String longTitle, String shortTitle,
				String viewName) {
			super(longTitle, shortTitle, viewName);
		}

		public ModelAndView test(HttpServletRequest request, Object command, Errors errors){
			ModelAndView mv = new ModelAndView("some_name");
			mv.getModel().put("ajax", "test");
			return mv;
		}
	}
	
}
