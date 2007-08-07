package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class RoutineAeTab extends AeRoutTab {

    private CtcDao ctcDao;
    private CtcTermDao ctcTermDao;

    public RoutineAeTab() {
        super("Enter basic AE information", "Adverse Events", "ae/terms");

    }
    
    @Override
    public void postProcess(HttpServletRequest request, RoutineAdverseEventInputCommand command, Errors errors) {
    	handleTermAction(command, request.getParameter("_action"),
            request.getParameter("_selected"));
    }
    
    private void handleTermAction(RoutineAdverseEventInputCommand c, String action, String selected){
    	
    	  if ("addTerm".equals(action)) {
    		  for (String ctcTermId : c.getCtcTermIds()) {
    		  CtcTerm ctcTerm = ctcTermDao.getById(Integer.parseInt(ctcTermId));
    		  AdverseEvent ae = new AdverseEvent();
    		  ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
    		  c.getAeRoutineReport().addAdverseEvent(ae);
    		  }
    	  }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(RoutineAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("ctcVersions", ctcDao.getAll());
        refdata.put("hospitalization", Hospitalization.values());
        refdata.put("attribution", Attribution.values());
        refdata.put("grade", Grade.values());
        return refdata;
    }

    ////// CONFIGURATION

    @Required
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    // for testing
    CtcDao getCtcDao() {
        return ctcDao;
    }

	public CtcTermDao getCtcTermDao() {
		return ctcTermDao;
	}

	@Required
	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}
    
    
}
