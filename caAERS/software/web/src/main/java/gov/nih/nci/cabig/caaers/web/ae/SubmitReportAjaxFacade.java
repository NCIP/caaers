
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This is the ajax facade class for the SubmitReportController flow.
 * @author Sameer Sawant
 */
public class SubmitReportAjaxFacade extends CreateAdverseEventAjaxFacade{
	
	private static final Log log = LogFactory.getLog(SubmitReportAjaxFacade.class);
    private static Class<?>[] CONTROLLERS = {SubmitReportController.class};
    
    /**
     * This method fetches the ReportStatus of the report. Its used in the SubmitReportController page.
     * @param Integer aeReportId
     * @return
     */
    public AjaxOutput fetchReportSubmissionStatus(String aeReportId, String reportIndex){
    	AjaxOutput output = new AjaxOutput();
    	EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) extractCommand();
    	ExpeditedAdverseEventReport aeReport = aeReportDao.getById(Integer.parseInt(aeReportId));
    	Report report = aeReport.getReports().get(Integer.parseInt(reportIndex));
    	Map<String, String> params = new LinkedHashMap<String, String>();
    	if(report.getLastVersion().getReportStatus().equals(ReportStatus.FAILED)){
    		output.setHtmlContent(renderAjaxView("reportSubmissionStatus", Integer.parseInt(aeReportId), params));
    		output.setObjectContent("FAILED");
    	}else if(report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)){
    		output.setHtmlContent(renderAjaxView("reportSubmissionStatus", Integer.parseInt(aeReportId), params));
    		output.setObjectContent("COMPLETED");
    	}
    	return output;
    }
    
    @Override
    public Class<?>[] controllers() {
        return CONTROLLERS;
    }
}