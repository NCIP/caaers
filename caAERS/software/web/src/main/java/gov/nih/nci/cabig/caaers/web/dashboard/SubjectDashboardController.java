/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ion C. Olaru
 */
public class SubjectDashboardController extends DashboardController {

    ParticipantRepository participantRepository;
    int MAX_RESULTS = 20;

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("dashboard/dashboard_Subject");
        List<Participant> subjectList = null;
        if (request.getParameter("loadAll") != null)
            subjectList = participantRepository.getAll();
        else
            subjectList = participantRepository.getAll(0, MAX_RESULTS);
        mv.addObject("subjectList", subjectList);
        return mv;
    }

    public ParticipantRepository getParticipantRepository() {
        return participantRepository;
    }

    public void setParticipantRepository(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
}
