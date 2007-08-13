package gov.nih.nci.cabig.caaers.web.par;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_PARTICIPANT;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.notNull;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.participant.CreateParticipantController;
import junit.framework.TestCase;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases( { CREATE_PARTICIPANT })
public class NewParticipantControllerUnitTest extends TestCase {
	private final CreateParticipantController controller = new CreateParticipantController();

	private ParticipantDao participantDao;

	private OrganizationDao organizationDao;

	private StudySiteDao studySiteDao;

	private ListValues listValues;

	private MockHttpServletRequest request;

	private MockHttpServletResponse response;

	private ServletRequestDataBinder binder;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();

		participantDao = EasyMock.createMock(ParticipantDao.class);
		studySiteDao = EasyMock.createMock(StudySiteDao.class);
		organizationDao = EasyMock.createMock(OrganizationDao.class);
		controller.setListValues(new ListValues());
		controller.setParticipantDao(participantDao);
		controller.setStudySiteDao(studySiteDao);
		controller.setOrganizationDao(new OrganizationDao());
	}

	public void testViewOnGoodSubmit() throws Exception {
		try {
			controller.handleRequest(request, response);
			fail("Should have throught the RequestNotSupportedException");
		}
		catch (Exception e) {
			assertTrue(e instanceof HttpRequestMethodNotSupportedException);
		}
		request.setMethod("POST");
		request.addParameter("firstName", "Boston");
		request.addParameter("lastName", "Scott");
		request.addParameter("gender", "Male");
		request.addParameter("dateOfBirth", "2006-12-12");
		request.setParameter("_target1", "");

		ModelAndView mv = controller.handleRequest(request, response);
		assertEquals("par/par_create_participant", mv.getViewName());
	}

	public void testPostAndReturnCommand() throws Exception {
		request.setMethod("POST");
		participantDao.save((Participant) notNull());
		expectLastCall().atLeastOnce().asStub();

		ModelAndView mv = controller.handleRequest(request, response);

		Object command = mv.getModel().get("command");
		assertNotNull("Command not present in model: " + mv.getModel(), command);
	}
}
