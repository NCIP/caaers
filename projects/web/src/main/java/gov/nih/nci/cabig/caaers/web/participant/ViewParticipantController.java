package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

// import Apache commons
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//Spring imports
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestUtils;

//java servlet imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

public class ViewParticipantController extends ParameterizableViewController {

    private static Log log = LogFactory.getLog(ViewParticipantController.class);
    private ParticipantDao participantDao;
    
    public static final String TYPE_EDIT = "edit";
    public static final String TYPE_CREATE = "create";

    public ViewParticipantController() {
        setViewName("par/par_view");
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String type = ServletRequestUtils.getStringParameter(request, "type");
//        request.getParameter("type");
        String msg = "You have successfully created a new subject.";

        setViewName("par/par_view");
        Participant participant = participantDao.getById(Integer.parseInt(request.getParameter("participantId")));
        //
        ParticipantInputCommand cmd = new EditParticipantCommand(participant);
        List<StudyParticipantAssignment> assignments = participant.getAssignments();

        // store StudySites from Participant object to Command object
        List<StudySite> studySites = new ArrayList<StudySite>();
        for (StudyParticipantAssignment studyParticipantAssignment : assignments) {
            studySites.add(studyParticipantAssignment.getStudySite());
        }
        cmd.setStudySites(studySites);

        if (participant.getAssignments().size() > 0)
            cmd.setOrganization(participant.getAssignments().get(0).getStudySite().getOrganization());
        //

        ModelAndView mav = new ModelAndView("par/par_view", "participant", participant);
        mav.getModelMap().addObject("command", cmd);

        if (type != null)
            if (type.equals(TYPE_EDIT))
                msg = "You have successfully updated the subject.";

        mav.getModel().put("flashMessage", msg);

        return mav;
    }
}
