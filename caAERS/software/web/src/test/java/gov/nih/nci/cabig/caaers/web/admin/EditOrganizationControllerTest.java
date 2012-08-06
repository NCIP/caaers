package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class EditOrganizationControllerTest extends AbstractTestCase {

	private EditOrganizationController editOrganizationController;
	private MockHttpSession session;
	private MockHttpServletRequest request;
	private OrganizationDao organizationDao;
	private OrganizationRepository organizationRepository;
	private Organization organization;
	private BindException errors;
	private final String SESSION_ATTR_NAME = "gov.nih.nci.cabig.caaers.web.admin.EditOrganizationController.FORM.command.to-replace";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		editOrganizationController = new EditOrganizationController();
		organizationDao = registerMockFor(OrganizationDao.class);
		organizationRepository = registerMockFor(OrganizationRepository.class);
		session = new MockHttpSession();
		request = new MockHttpServletRequest();
		request.setSession(session);
		editOrganizationController.setOrganizationDao(organizationDao);
		editOrganizationController.setOrganizationRepository(organizationRepository);
		organization = registerMockFor(Organization.class);
		errors = registerMockFor(BindException.class);
	}
	
	public void testIsSummaryEnabled() {
		assertTrue(editOrganizationController.isSummaryEnabled());
	}

	public void testProcessFinish1() throws Exception{
		EasyMock.expect(errors.hasErrors()).andReturn(true);
		replayMocks();
		ModelAndView mv = editOrganizationController.processFinish(request, new MockHttpServletResponse(), organization, errors);
		assertEquals("admin/organization_confirmation", mv.getViewName());
		verifyMocks();
	}
	
	public void testProcessFinish2() throws Exception{
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		EasyMock.expect(errors.getModel()).andReturn(new HashMap<String, Object>());
		organizationRepository.createOrUpdate(organization);
		replayMocks();
		ModelAndView mv = editOrganizationController.processFinish(request, new MockHttpServletResponse(), organization, errors);
		assertEquals("admin/organization_confirmation", mv.getViewName());
		assertEquals("Successfully updated the Organization", mv.getModel().get("flashMessage"));
		verifyMocks();
	}
	
	public void testProcessFinish3() throws Exception{
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		EasyMock.expect(errors.getModel()).andReturn(new HashMap<String, Object>());
		request.setParameter("_action", "saveRemoteOrg");
		request.setParameter("_selected", "0");
		EasyMock.expect(organization.getId()).andReturn(1);
		organizationDao.evict(organization);
		List<Organization> list = new ArrayList<Organization>();
		Organization remote = new LocalOrganization();
		list.add(remote);
		EasyMock.expect(organization.getExternalOrganizations()).andReturn(list);
		organizationRepository.convertToRemote(organization,remote);
		organization.setName(remote.getName());
		organization.setNciInstituteCode(remote.getNciInstituteCode());
		organization.setDescriptionText(remote.getDescriptionText());
		replayMocks();
		ModelAndView mv = editOrganizationController.processFinish(request, new MockHttpServletResponse(), organization, errors);
		assertEquals("admin/organization_confirmation", mv.getViewName());
		assertEquals("Successfully synched the Organization", mv.getModel().get("flashMessage"));
		verifyMocks();
	}
	
	public void testSave1() {
		EasyMock.expect(errors.hasErrors()).andReturn(true);
		replayMocks();		
		assertEquals(organization, editOrganizationController.save(organization, errors));
		verifyMocks();
	}
	
	public void testSave2() {
		EasyMock.expect(errors.hasErrors()).andReturn(false);
		organizationRepository.createOrUpdate(organization);
		replayMocks();		
		assertEquals(organization, editOrganizationController.save(organization, errors));
		verifyMocks();
	}

	public void testFormBackingObjectHttpServletRequest() throws Exception{
		session.setAttribute(SESSION_ATTR_NAME, "pqr");
		request.setParameter("organizationId", "1");
		EasyMock.expect(organizationDao.getById(1)).andReturn(organization);
		replayMocks();
		assertEquals(organization, editOrganizationController.formBackingObject(request));
		assertNull(session.getAttribute(SESSION_ATTR_NAME));
		verifyMocks();
	}

}
