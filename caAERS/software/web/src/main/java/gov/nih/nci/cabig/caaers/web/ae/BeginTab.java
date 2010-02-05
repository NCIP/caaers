package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.WorkFlowTab;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class BeginTab<T extends AdverseEventInputCommand> extends WorkFlowTab<T> {

	private static final String SELECTED_STUDY_ID = "pre_selected_study_id";
	private static final String SELECTED_PARTICIPANT_ID = "pre_selected_participant_id";
	private static final String SELECTED_COURSE_ID = "pre_selected_reporting_period_id";
	
    String instructions;

    public BeginTab() {
      this(null);

    }

    public BeginTab(String instructions) {
        super("Select study, subject and course/cycle", "Study,&nbsp;Subject &amp; Course/Cycle", "ae/selectAssignment");
        this.instructions = instructions;
    }

    public void onBind(HttpServletRequest request, T command, Errors errors) {
        super.onBind(request, command, errors);
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("pageTitle", getLongTitle());
        refdata.put("bodyTitle", getLongTitle()); // TODO: this should incorporate the flow name
        refdata.put("instructions",this.instructions != null ? this.instructions
                                   : "In order to create or edit an AE or SAE, you need to first select a subject and a\n"
                                   + "study. You may start with either one. Once you have selected one, the options\n"
                                   + "for the other will be automatically constrained.");
        return refdata;
    }

    @Override
    public void validate(T command, Errors errors) {
        boolean noStudy = command.getStudy() == null;
        boolean noParticipant = command.getParticipant() == null;
        boolean noAdverseEventReportingPeriod = command.getAdverseEventReportingPeriod() == null;
        if (noStudy) errors.rejectValue("study", "SAE_001", "Missing study");
        if (noParticipant) errors.rejectValue("participant", "SAE_002", "Missing subject");
        if (noAdverseEventReportingPeriod){
        	errors.rejectValue("adverseEventReportingPeriod", "SAE_003", "Missing course/cycle");
        }else{
        	if(command.getAdverseEventReportingPeriod().getTreatmentAssignment() != null && command.getAdverseEventReportingPeriod().getTreatmentAssignment().isRetired()){
        		errors.reject("SAE_031", "Treatment assignment to which this course is associated to, is removed from protocol.");
        	}
        }
        
    }

	@Override
	public void postProcess(HttpServletRequest request, T command, Errors errors) {
		super.postProcess(request, command, errors);
		
		//save the ids of the study/participant/couse for future use.
		if(!errors.hasErrors()){
			HttpSession session = request.getSession();
			session.setAttribute(SELECTED_STUDY_ID, command.getStudy().getId());
			session.setAttribute(SELECTED_PARTICIPANT_ID, command.getParticipant().getId());
			session.setAttribute(SELECTED_COURSE_ID, command.getAdverseEventReportingPeriod().getId());
		}
	}
    
    
    
    
}
