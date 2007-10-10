package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.ErrorMessages;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportService;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

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

    @Override
    public Map<String, Object> referenceData(ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        Map<Integer, ErrorMessages> reportMessages = new HashMap<Integer, ErrorMessages>();
        for (Report report : command.getAeReport().getReports()) {
            reportMessages.put(report.getId(), evaluationService.isSubmittable(report));
        }
        refdata.put("reportMessages", reportMessages);
        return refdata;
    }

    //  Set a property in the Report object to provide a clean way of accessing this on the JSP
    private void updateReports(ExpeditedAdverseEventInputCommand command){
    	
    		for (Report report : command.getAeReport().getReports()) {
    			ErrorMessages errorMessages = evaluationService.isSubmittable(report);
    			report.setDataMissing(errorMessages.hasErrors());
			}
    }
    
    private void handleWithdrawAction(ExpeditedAdverseEventInputCommand command, String action, String selected) {
        if ("withdraw".equals(action) ) {
        	
        	for (Report report : command.getAeReport().getReports()) {
                // TODO: there's no chance this actually works -- report.getId() is an Integer and selected is a String
            	if (report.getId().equals(selected) && !report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)){
            		reportService.withdrawLastReportVersion(report);
         	        break;
         		}
    		}
        }
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        // No fields for this tab
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.SUBMIT_REPORT_SECTION;
    }

    ////// CONFIGURATION

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
}
