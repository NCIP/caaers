/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
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
            if(section.equals(ExpeditedReportSection.ATTRIBUTION_SECTION) && !command.shouldValidateAttributions())  continue;

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


    /**
     * Will return true if the user is an Expedited Report Reviewer at the Cooordinating Center.
     * @return
     */
    private Boolean isUserAEReviewer(Study study){

        boolean userAEReviewer = false;
        String loginId = SecurityUtils.getUserLoginName();
        boolean isReportReviewer = SecurityUtils.checkAuthorization(UserGroupType.ae_expedited_report_reviewer);

        //now check if the sae coordinator is associated to the coordinaoting center
        if(isReportReviewer && study != null){
            Organization ccOrg = study.getStudyCoordinatingCenter().getOrganization();
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
    
    private Map<Integer, Boolean> initializeRenderSubmitLinkMap(ExpeditedAdverseEventInputCommand command){
    	Map<Integer, Boolean> renderSubmitLink = new HashMap<Integer, Boolean>();


    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	// check if the logged in user is a reviewer at the CC
        boolean isReviewerAtCC = isUserAEReviewer(command.getStudy());
        
    	boolean canLoggedInUserSubmit = isSuperUser || SecurityUtils.checkAuthorization(UserGroupType.ae_reporter) || isReviewerAtCC;

    	//First of all initialize renderSubmitLink map with the values based on the report status
    	for(Report report: command.getAeReport().getActiveReports()){
    		renderSubmitLink.put(report.getId(), canLoggedInUserSubmit &&
                    (report.getStatus().equals(ReportStatus.PENDING) || report.getStatus().equals(ReportStatus.FAILED)) );
    	}
    	
    	// If the logged in person is superUser then he is allowed to submit all the reports based on whether its complete/incomplete
    	// so simply return in this case
    	if(isSuperUser) return renderSubmitLink;
    	
        // Now we have to check again whether the worklow is enabled for each individual report
        // If the workflow is disabled for a report then canLoggedInUserSubmit will determine the reportSubmittability
        // else if the workflow is enabled for a report then check if the logged in user can submit the report and the report is submittable i.e.
    	// it should be in Finalize Report and Submit status.
        for(Report report: command.getAeReport().getActiveReports()){
            if(report.isWorkflowEnabled()){
                boolean canSubmit = renderSubmitLink.get(report.getId());
                renderSubmitLink.put(report.getId(), canSubmit && (report.getReviewStatus() == ReviewStatus.FINALIZE_REPORT_AND_SUBMIT));
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
    	InputField caseNumber = InputFieldFactory.createTextField("caseNumber", "Case number", false);
        InputFieldAttributes.setSize(caseNumber, 45);
        creator.createRepeatingFieldGroup("main", "reports", caseNumber);
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
