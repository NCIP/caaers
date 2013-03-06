/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
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

    private Boolean userAEReviewer;


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

    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	if(isSuperUser) return; //super user can submit always, but based on data entry status. 
    	
    	boolean canLoggedInUserSubmit = SecurityUtils.checkAuthorization(UserGroupType.ae_reporter,
				UserGroupType.ae_expedited_report_reviewer);

        //now check if the workflow is enabled on the report ?
        for(ExpeditedAdverseEventReport aeReport: getAssignment().getAeReports()){
            for(Report report : aeReport.getReports()){
                boolean canSubmit = reportsSubmittable.get(report.getId());  //only AEReporter and AEReportReviewer can submit
                reportsSubmittable.put(report.getId(), canSubmit && canLoggedInUserSubmit);
                if(report.isWorkflowEnabled()){
                   reportsSubmittable.put(report.getId(), canSubmit && isUserAEReviewer()); //only AEReportReviewer can submit
                }
            }
        }
    	
    }
    
    public void updateOptions() {
    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	if(isSuperUser || SecurityUtils.checkAuthorization(UserGroupType.ae_study_data_reviewer ,UserGroupType.ae_reporter)){
    		setAmendAnOption(true);
    	} else {
    		setAmendAnOption(false);
    	}
    	
    }

    /**
     * Will return true if the user is an Expedited Report Reviewer at the Cooordinating Center.
     * @return
     */
    private Boolean isUserAEReviewer(){
       if(userAEReviewer != null) return userAEReviewer;

        userAEReviewer = false;
        String loginId = SecurityUtils.getUserLoginName();
        boolean isReportReviewer = SecurityUtils.checkAuthorization(UserGroupType.ae_expedited_report_reviewer);

        //now check if the sae coordinator is associated to the coordinaoting center
        if(isReportReviewer && getStudy() != null){
            Organization ccOrg = getStudy().getStudyCoordinatingCenter().getOrganization();
            ResearchStaff researchStaff = researchStaffDao.getByLoginId(loginId);
            if(researchStaff != null && ccOrg != null){
                for(SiteResearchStaff siteRs : researchStaff.getSiteResearchStaffsInternal()){
                    if(siteRs.getOrganization().getId().equals(ccOrg.getId())){
                        userAEReviewer = true;
                        break;
                    }
                }
            }
        }

       return userAEReviewer;
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
