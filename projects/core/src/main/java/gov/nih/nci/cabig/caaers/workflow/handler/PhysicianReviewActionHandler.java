package gov.nih.nci.cabig.caaers.workflow.handler;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;

import java.util.Map;

import org.jbpm.graph.def.Action;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;


/**
 * This is the action handler that populates that data (eg. Physician Sign-off) once the physician 
 * approves or rejects a report he has received for review.
 * @author Sameer Sawant
 *
 */
public class PhysicianReviewActionHandler extends Action{
	private static String VAR_EXPEDITED_REPORT_ID = "var_expedited_report_id";
	private static String VAR_WF_TYPE = "var_wf_type";
	private static String PHYSICIAN_APPROVE_TRANSITION = "Approve Report";
	private static String ADDITIONAL_INFO_NEEDED_TRANSITION = "Request Additional Information";
	ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	
	public void execute(ExecutionContext context) throws Exception{
		ProcessInstance pInstance = context.getProcessInstance();
		Map<Object, Object> contextVariables = pInstance.getContextInstance().getVariables();
		if(contextVariables != null){
			String workflowType = (String) contextVariables.get(VAR_WF_TYPE);
			if(workflowType != null && workflowType.equals(ExpeditedAdverseEventReport.class.getName())){
				Integer expeditedReportId = (Integer) contextVariables.get(VAR_EXPEDITED_REPORT_ID);
				ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(expeditedReportId);
				for(Report report: aeReport.getReports()){
					if(report.getLastVersion().getReportStatus().equals(ReportStatus.PENDING) || report.getLastVersion().getReportStatus().equals(ReportStatus.FAILED)){
						if(context.getTransition().getName().equals(ADDITIONAL_INFO_NEEDED_TRANSITION))
							report.getLastVersion().setPhysicianSignoff(false);
						else if(context.getTransition().getName().equals(PHYSICIAN_APPROVE_TRANSITION))
							report.getLastVersion().setPhysicianSignoff(true);
					}
				}
				expeditedAdverseEventReportDao.save(aeReport);
			}
		}
	}
	
	public void setExpeditedAdverseEventReportDao(ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao){
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
}