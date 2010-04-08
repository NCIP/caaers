package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.security.RoleCheck;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
public class ViewReportTab extends AeTab {

	private ResearchStaffDao researchStaffDao;
	
    public ViewReportTab() {
        super("Submission", ExpeditedReportSection.SUBMIT_REPORT_SECTION.getDisplayName(),"ae/submit");
    }

    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Errors errors) {
        handleWithdrawAction(command, request.getParameter("_action"), request.getParameter("_selected"));
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {

        Map<String, Object> refdata = super.referenceData(request,command);
        Map<Integer, ReportSubmittability> reportMessages = new HashMap<Integer, ReportSubmittability>();

        // evaluate business rules.
        ReportSubmittability commonReportSubmittability = new ReportSubmittability();
        for (ExpeditedReportSection section : ExpeditedReportSection.values()) {

            if (!section.isAssociatedToBusinessRules()) continue;

            ValidationErrors validationErrors = evaluationService.validateReportingBusinessRules( command.getAeReport(), section);
            for (ValidationError vError : validationErrors.getErrors()) {
            	if(command.isErrorApplicable(vError.getFieldNames())){
            		commonReportSubmittability.addValidityMessage(section, messageSource.getMessage(vError.getCode(), vError.getReplacementVariables(), vError.getMessage(), Locale.getDefault()));
            	}
            }
        }

        //reportMessages.put(ExpeditedAdverseEventInputCommand.ZERO, commonReportSubmittability);

        // -- check the report submittability
        for (Report report : command.getAeReport().getActiveReports()) {
        	ReportSubmittability reportSubmittability = reportValidationService.isSubmittable(report);
            reportMessages.put(report.getId(), reportSubmittability);
            // Merge the commonValidationErros with the errors for individual reports.
            if(!commonReportSubmittability.getMessages().isEmpty()){
            	for(ExpeditedReportSection section: commonReportSubmittability.getMessages().keySet()){
            		if(reportSubmittability.getMessages().containsKey(section))
            			reportSubmittability.getMessages().get(section).addAll(commonReportSubmittability.getMessages().get(section));
            		else
            			reportSubmittability.getMessages().put(section, commonReportSubmittability.getMessages().get(section));
            	}
            }
        }
        refdata.put("reportMessages", reportMessages);
        
        Map<Integer, Boolean> renderSubmitLink = initializeRenderSubmitLinkMap(command);
        refdata.put("renderSubmitLink", renderSubmitLink);
        
    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	refdata.put("isSuperUser", isSuperUser);
    	
        return refdata;
    }
    
    private Map<Integer, Boolean> initializeRenderSubmitLinkMap(ExpeditedAdverseEventInputCommand command){
    	Map<Integer, Boolean> renderSubmitLink = new HashMap<Integer, Boolean>();
    	
    	String loginId = SecurityUtils.getUserLoginName();
    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	
    	//First of all initialize renderSubmitLink map with the values based on the report status
    	for(Report report: command.getAeReport().getActiveReports()){
    		renderSubmitLink.put(report.getId(), report.getStatus().equals(ReportStatus.PENDING) || report.getStatus().equals(ReportStatus.FAILED));
    	}
    	
    	// If the logged in person is superUser then he is allowed to submit all the reports based on whether its complete/incomplete
    	// so simply return in this case
    	if(isSuperUser) return renderSubmitLink;
    	
    	boolean canLoggedInUserSubmit = SecurityUtils.checkAuthorization(UserGroupType.caaers_ae_cd,
				UserGroupType.caaers_participant_cd,UserGroupType.caaers_central_office_sae_cd);
    	
    	// We update reportSubmittability based on workflow if the workflow is enabled at the System level first
    	if(command.getWorkflowEnabled()){
    		boolean isSAECoordinator = SecurityUtils.checkAuthorization(UserGroupType.caaers_central_office_sae_cd); 
    		boolean isSAECoordinatorAtCC = false;
			//now check if the sae coordinator is associated to the coordinaoting center
			if(isSAECoordinator && command.getStudy() != null){
				Organization ccOrg = command.getStudy().getStudyCoordinatingCenter().getOrganization();
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
    		for(Report report: command.getAeReport().getActiveReports()){
    			if(report.getWorkflowId() != null){
    				// This implies the report is in a workflow
    		   			boolean canSubmit = renderSubmitLink.get(report.getId());
    		   			renderSubmitLink.put(report.getId(), canSubmit && isSAECoordinatorAtCC);
    			}else{
    				// This implies that the report is not in a workflow
    		   			boolean canSubmit = renderSubmitLink.get(report.getId());
    		   			renderSubmitLink.put(report.getId(), canSubmit && canLoggedInUserSubmit);
    			}
    		}
    	}else{
    		// Update reportSubmittablity based on whether the logged in person can submit
    		for(Integer id: renderSubmitLink.keySet()){
    			boolean canSubmit = renderSubmitLink.get(id);
    			renderSubmitLink.put(id, canSubmit && canLoggedInUserSubmit);
    		}
    	}
    	return renderSubmitLink;
    }

    private void handleWithdrawAction(ExpeditedAdverseEventInputCommand command, String action,String selected) {
        if ("withdraw".equals(action)) {

            for (Report report : command.getAeReport().getReports()) {
                // TODO: there's no chance this actually works -- report.getId() is an Integer and
                // selected is a String
                if (report.getId().equals(selected) && !report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)) {
                    reportRepository.withdrawReport(report);
                    break;
                }
            }
        }
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        // Note:- Do not call super, as it will do the report level validations.
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,ExpeditedAdverseEventInputCommand command) {
        // No fields for this tab
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.SUBMIT_REPORT_SECTION};
    }

    // //// CONFIGURATION
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao){
    	this.researchStaffDao = researchStaffDao;
    }
    
    public ResearchStaffDao getResearchStaffDao(){
    	return researchStaffDao;
    }
    
}
