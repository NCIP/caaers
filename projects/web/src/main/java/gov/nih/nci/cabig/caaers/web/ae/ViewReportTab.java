package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.ErrorMessages;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Krikor Krumlian
 */
public class ViewReportTab extends AeTab {
	private EvaluationService evaluationService;

    public ViewReportTab() {
        super("Submission", "Submit", "ae/submit");

    }
    
    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Errors errors) {

        handleWithdrawAction(command, request.getParameter("_action"),
            request.getParameter("_selected"));
    }
    
    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
       updateReports(command);
    }
    
    //  Set a property in the Report object to provide a clean way of accessing this on the JSP
    private void updateReports(ExpeditedAdverseEventInputCommand command){
    	
    		for (Report report : command.getAeReport().getReports()) {
    			ErrorMessages errorMessages = evaluationService.isSubmittable(report);
    			report.setDataMissing(errorMessages.hasErrors());
    			System.out.println(errorMessages.hasErrors());
			}
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
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap groups = new InputFieldGroupMap();
        return groups;
    }

    ////// CONFIGURATION

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.SUBMIT_REPORT_SECTION;
    }
}
