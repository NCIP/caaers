package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Participant;

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

public class ViewParticipantController extends ParameterizableViewController {

    private static Log log = LogFactory.getLog(ViewParticipantController.class);
    private ParticipantDao participantDao;
    
    public static final String TYPE_EDIT = "edit";
    public static final String TYPE_CREATE = "create";

    public ViewParticipantController() {
        setViewName("par/par_confirm");
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

        setViewName("par/par_confirm");
        Participant participant = participantDao.getById(Integer.parseInt(request.getParameter("participantId")));
        ModelAndView mav = new ModelAndView("par/par_confirm", "participant", participant);

        if (type != null)
            if (type.equals(TYPE_EDIT))
                msg = "You have successfully updated the subject.";

        mav.getModel().put("flashMessage", msg);

        return mav;
    }
}
