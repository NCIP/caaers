/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.tools.spring.tabbedflow;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class AutomaticSaveAjaxableFormControllerTest extends AbstractTestCase {
	
	private AutomaticSaveAjaxableFormController automaticSaveAjaxableFormController;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private Errors errors;
	private WorkFlowTab firstTab;
	private WorkFlowTab secondTab;
	private Object command;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		errors = registerMockFor(Errors.class);
		firstTab = new WorkFlowTab(){
			@Override
			public Map referenceData(Object command) {
				return new HashMap(){{put("first tab", "test");}};
			}
			
			@Override
			public ModelAndView postProcessAsynchronous(
					HttpServletRequest request, Object command, Errors error)
					throws Exception {
				ModelAndView mv = new ModelAndView("some_name");
				mv.getModel().put("ajax", "test");
				return mv;
			}
		};
		secondTab = new WorkFlowTab(){
			@Override
			public Map referenceData(Object command) {
				return new HashMap(){{put("second tab", "test");}};
			}
			
		};
		command = new Object();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		automaticSaveAjaxableFormController = new AutomaticSaveAjaxableFormController() {

			@Override
			protected MutableDomainObjectDao getDao() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected MutableDomainObject getPrimaryDomainObject(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected ModelAndView processFinish(HttpServletRequest arg0,
					HttpServletResponse arg1, Object arg2, BindException arg3)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FlowFactory getFlowFactory() {
				return new FlowFactory() {
					public Flow createFlow(Object cmd) {
						
		            	/**
		            	 * Third level tabs are secured now , Any changes in this flow needs to reflect in 
		            	 * applicationContext-web-security.xml <util:map id="tabObjectPrivilegeMap"> 
		            	 */
						Flow flow = new Flow("Test Flow");
						flow.addTab(firstTab);
						flow.addTab(secondTab);
						return flow;
					}
				};
			}
			
			@Override
		    protected boolean shouldSave(HttpServletRequest request, Object command, Tab tab) {
		        return  false;
		    }
		};
		automaticSaveAjaxableFormController.setCommandClass(Object.class);
	}

	public void testHandleRequestInternalHttpServletRequestHttpServletResponse() throws Exception{
		ModelAndView mv = automaticSaveAjaxableFormController.handleRequestInternal(request, response);
		assertNotNull(mv.getModel().get("command"));
		request.addParameter("_asynchronous", "true");
		request.setMethod("POST");
		request.addParameter("_page", "0");
		mv = automaticSaveAjaxableFormController.handleRequestInternal(request, response);
		assertEquals("test", mv.getModel().get("ajax"));
	}

	public void testReferenceDataHttpServletRequestObjectErrorsInt() throws Exception {
		request.setParameter("_page", "1");
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		replayMocks();
		Map refdata = automaticSaveAjaxableFormController.referenceData(request, command, errors, 1);
		assertEquals("Information saved successfully", refdata.get("flashMessage"));
		assertEquals("test", refdata.get("second tab"));
		verifyMocks();
	}

	public void testPopulateSaveConfirmationMessage1() {
		Map refdata = new HashMap();
		EasyMock.expect(errors.hasErrors()).andReturn(true);
		replayMocks();
		automaticSaveAjaxableFormController.populateSaveConfirmationMessage(refdata, request, new Object(), errors, 1);
		assertTrue(refdata.isEmpty());
		verifyMocks();
	}
	
	public void testPopulateSaveConfirmationMessage2() {
		Map refdata = new HashMap();
		refdata.put("flashMessage", "Something");
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		replayMocks();
		automaticSaveAjaxableFormController.populateSaveConfirmationMessage(refdata, request, new Object(), errors, 1);
		assertEquals("Something", refdata.get("flashMessage"));
		verifyMocks();
	}
	
	public void testPopulateSaveConfirmationMessage3() {
		Map refdata = new HashMap();
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		replayMocks();
		automaticSaveAjaxableFormController.populateSaveConfirmationMessage(refdata, request, new Object(), errors, 1);
		assertTrue(refdata.isEmpty());
		verifyMocks();
	}
	
	public void testPopulateSaveConfirmationMessage4() {
		request.setParameter("_page", "0");
		Map refdata = new HashMap();
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		replayMocks();
		automaticSaveAjaxableFormController.populateSaveConfirmationMessage(refdata, request, new Object(), errors, 0);
		assertTrue(refdata.isEmpty());
		verifyMocks();
	}
	
	public void testPopulateSaveConfirmationMessage5() {
		request.setParameter("_page", "1");
		Map refdata = new HashMap();
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		replayMocks();
		automaticSaveAjaxableFormController.populateSaveConfirmationMessage(refdata, request, new Object(), errors, 1);
		assertEquals("Information saved successfully", refdata.get("flashMessage"));
		verifyMocks();
	}
	
	public void testPopulateSaveConfirmationMessage6() {
		request.setParameter("_page", "1");
		request.setAttribute("tabFlashMessage", "Another test");
		Map refdata = new HashMap();
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		replayMocks();
		automaticSaveAjaxableFormController.populateSaveConfirmationMessage(refdata, request, new Object(), errors, 1);
		assertEquals("Another test", refdata.get("flashMessage"));
		verifyMocks();
	}

	public void testPostProcessPageHttpServletRequestObjectErrorsInt() {
//		fail("Not yet implemented");
	}

	public void testIsAjaxRequest() {
		assertFalse(automaticSaveAjaxableFormController.isAjaxRequest(request));
		request.addParameter("_asynchronous", "true");
		assertTrue(automaticSaveAjaxableFormController.isAjaxRequest(request));
	}

	public void testSetAjaxModelAndView() {
		ModelAndView mv = new ModelAndView();
		automaticSaveAjaxableFormController.setAjaxModelAndView(request, mv);
		assertEquals(mv, request.getAttribute("async_model_and_view"));
	}

	public void testGetAjaxModelAndView() {
		ModelAndView mv = new ModelAndView();
		request.setAttribute("async_model_and_view", mv);
		assertEquals(mv, automaticSaveAjaxableFormController.getAjaxModelAndView(request));
	}

	public void testIsAjaxResponseFreeText() {
		assertTrue(automaticSaveAjaxableFormController.isAjaxResponseFreeText(new ModelAndView()));
		assertFalse(automaticSaveAjaxableFormController.isAjaxResponseFreeText(new ModelAndView("some view name")));
	}

	public void testRespondAjaxFreeText() throws Exception{
		ModelAndView mv = registerMockFor(ModelAndView.class);
		EasyMock.expect(mv.getModel()).andReturn(new HashMap(){{put("free_text","text");}});
		replayMocks();
		automaticSaveAjaxableFormController.respondAjaxFreeText(mv, response);
		verifyMocks();
	}

	public void testGetAjaxRequestParamName() {
		assertEquals("_asynchronous", automaticSaveAjaxableFormController.getAjaxRequestParamName());
	}

	public void testGetAjaxModelAndViewAttr() {
		assertEquals("async_model_and_view", automaticSaveAjaxableFormController.getAjaxModelAndViewAttr());
	}

	public void testGetFreeTextModelName() {
		assertEquals("free_text", automaticSaveAjaxableFormController.getFreeTextModelName());
	}

}
