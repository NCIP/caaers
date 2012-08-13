package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.easymock.EasyMock;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class SubjectDashboardControllerTest extends WebTestCase {

    ParticipantRepository participantRepository;
    SubjectDashboardController controller;
    List<Participant> subjectList = new ArrayList<Participant>();
    public void setUp() throws Exception {
        super.setUp();
        participantRepository = registerMockFor(ParticipantRepository.class);
        controller = new SubjectDashboardController();
        controller.setParticipantRepository(participantRepository);

    }

    public void testHandleRequestInternal_LoadAll() throws Exception {
        EasyMock.expect(participantRepository.getAll()).andReturn(subjectList);
        replayMocks();
        request.setParameter("loadAll", "true");
        ModelAndView mv = controller.handleRequest(request , response);
        assertSame(subjectList,mv.getModel().get("subjectList"));
        verifyMocks();
    }


    public void testHandleRequestInternal() throws Exception {
        EasyMock.expect(participantRepository.getAll(0, 20)).andReturn(subjectList);
        replayMocks();
        ModelAndView mv = controller.handleRequest(request , response);
        assertSame(subjectList,mv.getModel().get("subjectList"));
        verifyMocks();
    }
}
