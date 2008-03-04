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
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.participant.CreateParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.NewParticipantCommand;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases( { CREATE_PARTICIPANT })
public class NewParticipantControllerTest extends WebTestCase {
    private final CreateParticipantController controller = new CreateParticipantController();

    private ParticipantDao participantDao;

    private OrganizationDao organizationDao;

    private StudySiteDao studySiteDao;

    private ListValues listValues;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        participantDao = registerMockFor(ParticipantDao.class);
        studySiteDao = registerMockFor(StudySiteDao.class);
        organizationDao = registerDaoMockFor(OrganizationDao.class);
        // controller.setListValues(new ListValues());
        // controller.setParticipantDao(participantDao);
        controller.setStudySiteDao(studySiteDao);
        controller.setOrganizationDao(organizationDao);
    }

    public void testViewOnGoodSubmit() throws Exception {
        // request.addParameter("firstName", "Boston");
        // request.addParameter("lastName", "Scott");
        // request.addParameter("gender", "Male");
        // request.addParameter("dateOfBirth", "2006-12-12");
        // request.setParameter("_target1", "");
        //
        // ModelAndView mv = controller.handleRequest(request, response);
        // assertEquals("par/par_create_participant", mv.getViewName());
    }

    private NewParticipantCommand postAndReturnCommand() throws Exception {
        request.setMethod("POST");
        participantDao.save((Participant) notNull());
        expectLastCall().atLeastOnce().asStub();

        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();

        Object command = mv.getModel().get("command");
        assertNotNull("Command not present in model: " + mv.getModel(), command);
        return (NewParticipantCommand) command;
    }
}
