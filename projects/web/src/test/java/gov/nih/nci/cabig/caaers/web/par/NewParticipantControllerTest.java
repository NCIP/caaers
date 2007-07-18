package gov.nih.nci.cabig.caaers.web.par;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.easymock.classextension.EasyMock;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_PARTICIPANT;
import static org.easymock.classextension.EasyMock.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.participant.CreateParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.NewParticipantCommand;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ CREATE_PARTICIPANT })
public class NewParticipantControllerTest extends WebTestCase {
    private CreateParticipantController controller = new CreateParticipantController();
    private ParticipantDao participantDao;
    private StudySiteDao   studySiteDao;
    private ListValues listValues;

    protected void setUp() throws Exception {
        super.setUp();
        participantDao = registerMockFor(ParticipantDao.class);
        studySiteDao   = registerMockFor(StudySiteDao.class);
        controller.setListValues(new ListValues());
        controller.setParticipantDao(participantDao);
        controller.setStudySiteDao(studySiteDao);
    }
   
    public void testViewOnGoodSubmit() throws Exception {
        request.addParameter("firstName", "Boston");
        request.addParameter("lastName", "Scott");
        request.addParameter("gender", "Male");
        request.addParameter("dateOfBirth", "2006-12-12");
        request.setParameter("_target1", "");
        
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("par/par_create_participant", mv.getViewName());
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
