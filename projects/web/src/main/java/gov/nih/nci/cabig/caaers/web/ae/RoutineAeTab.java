package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.HashSet;
import java.util.ListIterator;
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
    	handleAction(command, request.getParameter("_action"),request.getParameter("_selected"),errors);
    }
    
    private void handleAction(RoutineAdverseEventInputCommand c, String action, String selected,Errors errors){
    	
    	  if ("addTerm".equals(action)) {
    		  for (String ctcTermId : c.getCtcTermIds()) {
    		  CtcTerm ctcTerm = ctcTermDao.getById(Integer.parseInt(ctcTermId));
    		  AdverseEvent ae = new AdverseEvent();
    		  ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
    		  if( uniqueAeValidate(c,errors,ae) ){
    			  c.getAeRoutineReport().addAdverseEvent(ae);
    		  }
    		  }
    	  }else{
    		  postProcessValidate(c, errors);
    	  }
    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(RoutineAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, RoutineAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        refdata.put("ctcVersions", ctcDao.getAll());
        refdata.put("hospitalization", Hospitalization.values());
        refdata.put("attribution", Attribution.values());
        refdata.put("grade", Grade.values());
        return refdata;
    }
    
    protected boolean uniqueAeValidate(RoutineAdverseEventInputCommand command, Errors errors , AdverseEvent adverseEvent) {
    	int index = 0;
    	boolean isAeUnique = true;
    	HashSet<Integer> hSet = new HashSet<Integer>();
    	 if (command.getAeRoutineReport().getAdverseEvents() != null) {
 			for (ListIterator<AdverseEvent> lit = command.getAeRoutineReport().getAdverseEvents().listIterator(); lit.hasNext();) {
 				AdverseEvent ae = lit.next();
 				hSet.add(ae.getAdverseEventCtcTerm().getCtcTerm().getId());
 			}
 			if(!hSet.add(adverseEvent.getAdverseEventCtcTerm().getCtcTerm().getId())){
 				errors.rejectValue("aeRoutineReport.adverseEvents["+ index +"].adverseEventTerm", "DUPLICATE", "CTC term you tried to add already exists");
 				return false;
 			}
    	 }
    	 return isAeUnique;
    }
    
    protected void postProcessValidate(RoutineAdverseEventInputCommand command, Errors errors) {
    	int index = 0;
    	if (command.getAeRoutineReport().getStartDate() == null){
    		errors.rejectValue("aeRoutineReport.startDate", "REQUIRED", "Missing From");
    	}
    	if (command.getAeRoutineReport().getEndDate() == null){
    		errors.rejectValue("aeRoutineReport.endDate", "REQUIRED", "Missing From");
    	}
    	
        if (command.getAeRoutineReport().getAdverseEvents() != null) {
			for (ListIterator<AdverseEvent> lit = command.getAeRoutineReport()
					.getAdverseEvents().listIterator(); lit.hasNext();) {
				AdverseEvent ae = lit.next();
				
				if (ae.getAdverseEventCtcTerm().getTerm().isOtherRequired() && ae.getDetailsForOther() == null && ae.getLowLevelTerm() == null){
		    		errors.rejectValue("aeRoutineReport.adverseEvents["+ index +"].detailsForOther", "REQUIRED", "Missing Other(Verbatim)");
		    		errors.rejectValue("aeRoutineReport.adverseEvents["+ index +"].lowLevelTerm", "REQUIRED", "Missing Other(MedDRA)");
		    	}
				if (ae.getGrade() == null){
		    		errors.rejectValue("aeRoutineReport.adverseEvents["+ index +"].grade", "REQUIRED", "Missing Grade");
		    	}
				if (ae.getAttributionSummary() == null){
		    		errors.rejectValue("aeRoutineReport.adverseEvents["+ index +"].attributionSummary", "REQUIRED", "Missing Attribution");
		    	}

				index++;
			}
		}
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
