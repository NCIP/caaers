package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.WorkFlowTab;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class BeginTab<T extends AdverseEventInputCommand> extends WorkFlowTab<T> {

    String instructions;

    public BeginTab() {
      this(null);

    }

    public BeginTab(String instructions) {
    	  super("Select study, subject and course", "Study,&nbsp;Subject &amp; Course", "ae/selectAssignment");
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
        if (noStudy) errors.rejectValue("study", "REQUIRED", "Missing study");
        if (noParticipant) errors.rejectValue("participant", "REQUIRED", "Missing subject");
        if (noAdverseEventReportingPeriod) errors.rejectValue("adverseEventReportingPeriod", "REQUIRED", "Missing course");
    }
    
    
}
