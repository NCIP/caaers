package gov.nih.nci.cabig.caaers.workflow.handler;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
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
	private static String VAR_WF_TYPE = "var_wf_type";
    private static String VAR_REPORT_ID = "var_report_id";
	private static String PHYSICIAN_APPROVE_TRANSITION = "Approve Report";
	private static String ADDITIONAL_INFO_NEEDED_TRANSITION = "Request Additional Information";
    
	private ReportDao reportDao;
	
	public void execute(ExecutionContext context) throws Exception{
		ProcessInstance pInstance = context.getProcessInstance();
		Map<Object, Object> contextVariables = pInstance.getContextInstance().getVariables();
		if(contextVariables != null){
			String workflowType = (String) contextVariables.get(VAR_WF_TYPE);
			if(workflowType != null && workflowType.equals(Report.class.getName())){
				Integer reportId = (Integer) contextVariables.get(VAR_REPORT_ID);
				Report report = reportDao.getById(reportId);
				if(report.getLastVersion().getReportStatus().equals(ReportStatus.PENDING) || report.getLastVersion().getReportStatus().equals(ReportStatus.FAILED)){
					if(context.getTransition().getName().equals(ADDITIONAL_INFO_NEEDED_TRANSITION))
						report.getLastVersion().setPhysicianSignoff(false);
					else if(context.getTransition().getName().equals(PHYSICIAN_APPROVE_TRANSITION))
					    report.getLastVersion().setPhysicianSignoff(true);
				}
                
				reportDao.save(report);
			}
		}
	}

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }
}