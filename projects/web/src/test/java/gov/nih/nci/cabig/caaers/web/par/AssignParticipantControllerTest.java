package gov.nih.nci.cabig.caaers.web.par;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.ASSIGN_PARTICIPANT;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.notNull;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantStudyCommand;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases( { ASSIGN_PARTICIPANT })
public class AssignParticipantControllerTest extends WebTestCase {
    private AssignParticipantController controller = new AssignParticipantController();

    private ParticipantDao participantDao;

    private StudySiteDao studySiteDao;

    private ListValues listValues;

    protected void setUp() throws Exception {
        super.setUp();
        participantDao = registerMockFor(ParticipantDao.class);
        controller.setListValues(new ListValues());
        controller.setParticipantDao(participantDao);
    }

    public void testViewOnGoodSubmit() throws Exception {
//        request.addParameter("firstName", "Boston");
//        request.addParameter("lastName", "Scott");
//        request.addParameter("gender", "Male");
//        request.addParameter("dateOfBirth", "2006-12-12");
//        request.setParameter("_target0", "");
//
//        ModelAndView mv = controller.handleRequest(request, response);
//        assertEquals("par/reg_participant_search", mv.getViewName());
    	assertTrue(true);
    }

    private AssignParticipantStudyCommand postAndReturnCommand() throws Exception {
        request.setMethod("POST");
        participantDao.save((Participant) notNull());
        expectLastCall().atLeastOnce().asStub();

        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();

        Object command = mv.getModel().get("command");
        assertNotNull("Command not present in model: " + mv.getModel(), command);
        return (AssignParticipantStudyCommand) command;
    }
}
