package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class ListAdverseEventsCommand {
    private StudyParticipantAssignment assignment;

    private Study study;

    private Participant participant;

    Map<Integer, Boolean> reportsSubmittable;
    
    Map<Integer, Boolean> dataEntryStatus;
    
    private boolean submitLinkRenderable;

    private boolean workflowEnabled = false;
    
    private ResearchStaffDao researchStaffDao;

    private ReportValidationService reportValidationService;
    
    private boolean amendAnOption;


	public ListAdverseEventsCommand(ReportValidationService reportValidationService, ResearchStaffDao researchStaffDao) {
        this.reportValidationService = reportValidationService;
        this.researchStaffDao = researchStaffDao;
        reportsSubmittable = new HashMap<Integer, Boolean>();
        dataEntryStatus = new HashMap<Integer, Boolean>();
    }

    // //// LOGIC

	public void updateSubmittabilityBasedOnReportStatus(){
		for(ExpeditedAdverseEventReport aeReport: assignment.getAeReports()){
			for(Report report : aeReport.getReports()){
				Boolean currentSubmittability = reportsSubmittable.get(report.getId());
				reportsSubmittable.put(report.getId(), currentSubmittability && (report.getStatus().equals(ReportStatus.PENDING) || report.getStatus().equals(ReportStatus.FAILED)));
			}
		}
	}

    public void updateSubmittability() {
        reportsSubmittable.clear();
        dataEntryStatus.clear();
        for (ExpeditedAdverseEventReport aeReport : assignment.getAeReports()) {
            for (Report report : aeReport.getReports()) {
                ReportSubmittability errorMessages = reportValidationService.isSubmittable(report);
                reportsSubmittable.put(report.getId(), errorMessages.isSubmittable());
            }
        }
        dataEntryStatus.putAll(reportsSubmittable);
    }
    
    public void updateSubmittabilityBasedOnWorkflow() {
    	String loginId = SecurityUtils.getUserLoginName();
    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	
    	// If the logged in person is superUser then he is allowed to submit all the reports based on whether its complete/incomplete
    	// so simply return in this case
    	if(isSuperUser) return;
    	
    	boolean canLoggedInUserSubmit = SecurityUtils.checkAuthorization(UserGroupType.caaers_ae_cd,
				UserGroupType.caaers_participant_cd,UserGroupType.caaers_central_office_sae_cd);
    	
    	// We update reportSubmittability based on workflow if the workflow is enabled at the System level first
    	if(getWorkflowEnabled()){
    		boolean isSAECoordinator = SecurityUtils.checkAuthorization(UserGroupType.caaers_central_office_sae_cd); 
    		boolean isSAECoordinatorAtCC = false;
			//now check if the sae coordinator is associated to the coordinaoting center
			if(isSAECoordinator && getStudy() != null){
				Organization ccOrg = getStudy().getStudyCoordinatingCenter().getOrganization();
				ResearchStaff researchStaff = researchStaffDao.getByLoginId(loginId);
				if(researchStaff != null && ccOrg != null){
			    	for(SiteResearchStaff siteRs : researchStaff.getSiteResearchStaffsInternal()){
			    		if(siteRs.getOrganization().getId().equals(ccOrg.getId())){
			    			isSAECoordinatorAtCC = true;
			    			break;
			    		}
			    	}
				}
			}
    		
    		// Now we have to check again whether the worklow is enabled for each individual report
			// If the workflow is disabled for a report then canLoggedInUserSubmit will determine the reportSubmittability
			// else if the workflow is enabled for a report then only SAE Coordinator at the coordinating center can submit
			// the report
    		for(ExpeditedAdverseEventReport aeReport: getAssignment().getAeReports()){
    			for(Report report: aeReport.getReports()){
    				if(report.getWorkflowId() != null){
    					// This implies the report is in a workflow
    		    			boolean canSubmit = reportsSubmittable.get(report.getId());
    		    			reportsSubmittable.put(report.getId(), canSubmit && isSAECoordinatorAtCC);
    				}else{
    					// This implies that the report is not in a workflow
    		    			boolean canSubmit = reportsSubmittable.get(report.getId());
    		    			reportsSubmittable.put(report.getId(), canSubmit && canLoggedInUserSubmit);
    				}
    			}
    		}
    	}else{
    		// Update reportSubmittablity based on whether the logged in person can submit
    		for(Integer id: reportsSubmittable.keySet()){
    			boolean canSubmit = reportsSubmittable.get(id);
    			reportsSubmittable.put(id, canSubmit && canLoggedInUserSubmit);
    		}
    	}
    }
    
    public void updateOptions() {
    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	if(isSuperUser || SecurityUtils.checkAuthorization(UserGroupType.caaers_data_cd , 
    			UserGroupType.caaers_ae_cd, UserGroupType.caaers_participant_cd)){
    		setAmendAnOption(true);
    	} else {
    		setAmendAnOption(false);
    	}
    	
    }
    public Map<Integer, Boolean> getReportsSubmittable() {
        return reportsSubmittable;
    }
    
    public Map<Integer, Boolean> getDataEntryStatus(){
    	return dataEntryStatus;
    }

    // //// BOUND PROPERTIES
    public StudyParticipantAssignment getAssignment() {
		return assignment;
	}
    public void setAssignment(StudyParticipantAssignment assignment) {
		this.assignment = assignment;
	}
    
    public Study getStudy() {
		return study;
	}
    public void setStudy(Study study) {
		this.study = study;
	}
    public Participant getParticipant() {
		return participant;
	}
    public void setParticipant(Participant participant) {
		this.participant = participant;
	}
    
    public boolean getIgnoreCompletedStudy() {
        return false;
    }
    
    public boolean isSubmitLinkRenderable() {
		return submitLinkRenderable;
	}
    public void setSubmitLinkRenderable(boolean submitLinkRenderable) {
		this.submitLinkRenderable = submitLinkRenderable;
	}
    
    public boolean getWorkflowEnabled(){
    	return workflowEnabled;
    }
    
    public void setWorkflowEnabled(boolean workflowEnabled){
    	this.workflowEnabled = workflowEnabled;
    }

	public boolean isAmendAnOption() {
		return amendAnOption;
	}

	public void setAmendAnOption(boolean amendAnOption) {
		this.amendAnOption = amendAnOption;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao){
		this.researchStaffDao = researchStaffDao;
	}
}
