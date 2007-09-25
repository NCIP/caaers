package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

/**
 * @author Krikor Krumlian
 */
public class ViewReportTab extends AeTab {
    //private RepeatingFieldGroupFactory fieldFactory;

    public ViewReportTab() {
        super("Submission", "Submit", "ae/submit");

    }
    
    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Errors errors) {

        handleWithdrawAction(command, request.getParameter("_action"),
            request.getParameter("_selected"));
    }
    
    private void handleWithdrawAction(ExpeditedAdverseEventInputCommand command, String action, String selected) {
        if ("withdraw".equals(action) ) {
        	
        	for (Report report : command.getAeReport().getReports()) {
            	if (report.getId().equals(selected) && !report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)){
            		reportService.withdrawLastReportVersion(report);
         	        break;
         		}
    		}
        }
    }
    
    

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap groups = new InputFieldGroupMap();
        return groups;
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.SUBMIT_REPORT_SECTION;
    }
}
