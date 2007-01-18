package gov.nih.nci.cabig.caaers.web;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudySite;


// Logging 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Krikor Krumlian
 */

public class NewParticipantController extends SimpleFormController {
	protected final Log log = LogFactory.getLog(getClass());
    private ParticipantDao participantDao;
    private StudySiteDao studySiteDao;
    private List<StudySite> studySites;
    
    public NewParticipantController() {
        setCommandClass(NewParticipantCommand.class);
        setFormView("createParticipant");
        setSuccessView("aParticipant");
    }
    
    protected void initBinder(HttpServletRequest request,
        ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(true));
        super.initBinder(request, binder);
    }
    
    /*
     * @return list of all study sites.
     */
    private List<StudySite> getStudySites()
    {
        studySites = studySiteDao.getAll();
    	return studySites;
    }
    
    
    protected Map<String, Object> referenceData(HttpServletRequest httpServletRequest) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        //can be probably loaded from a properties file ??
        Map<String, String> genders = new HashMap<String, String>();
        genders.put("Female", "Female");
        genders.put("Male", "Male");
        refdata.put("genders", genders);
        refdata.put("action", "New");
        refdata.put("studySites",getStudySites());
        return refdata;
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
        NewParticipantCommand participantCommand = (NewParticipantCommand) oCommand;
        Participant participant = participantCommand.createParticipant();
        participantDao.save(participant);
        
        ModelAndView modelAndView = new ModelAndView(getSuccessView());
        modelAndView.addObject("participant", participant);
		modelAndView.addAllObjects(errors.getModel());
		return modelAndView;
        //return new ModelAndView(new RedirectView(getSuccessView()), "id", 1);
        //return new ModelAndView(new RedirectView(getSuccessView()), "id", ServletRequestUtils.getIntParameter(request, "id"));
        //return new ModelAndView(new RedirectView(getSuccessView()), "newParticipant", participant);
    }

    ////// CONFIGURATION

    @Required
    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }
    
    @Required
    public void setStudySiteDao(StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }
}
