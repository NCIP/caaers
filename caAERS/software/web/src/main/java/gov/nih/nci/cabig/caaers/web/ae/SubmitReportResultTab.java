package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sameer Sawant
 */
public class SubmitReportResultTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {
	
	protected EvaluationService evaluationService;
	protected ReportDao reportDao;
	
	public SubmitReportResultTab() {
        super("Submission Result", "Submission Status", "ae/submitReportResult");

    }
	
	@Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
		InputFieldGroupMap map = new InputFieldGroupMap();
		return map;
	}
	
	 @Override
	public Map<String, Object> referenceData(HttpServletRequest request, ExpeditedAdverseEventInputCommand oCommand) {

		 SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) oCommand;
		 // Set the correct ReportVersion
		 Report report = reportDao.getById(command.getAeReport().getReports().get(Integer.parseInt(command.getReportIndex())).getId());
		 command.setLastVersion(report.getLastVersion());
		 command.setReportSubmitted(true);
	        
		 Map<String, Object> refdata = super.referenceData(request,command);
	     return refdata;
	 }
	 
	 public void setEvaluationService(EvaluationService evaluationService){
		 this.evaluationService = evaluationService;
	 }
	 
	 public void setReportDao(ReportDao reportDao){
		 this.reportDao = reportDao;
	 }
}