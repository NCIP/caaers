/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class SubmitReportTab extends TabWithFields<SubmitExpeditedAdverseEventCommand> {

	protected final Log log = LogFactory.getLog(getClass());
	protected ReportSubmissionService reportSubmissionService;
	
    public SubmitReportTab() {
        super("Submission", "Recipients", "ae/submitReport");
    }

    @Override
    public InputFieldGroupMap createFieldGroups(SubmitExpeditedAdverseEventCommand command) {

        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup ccReport = new DefaultInputFieldGroup("ccReport");
        InputField cc = InputFieldFactory.createTextArea("report.lastVersion.ccEmails", "Cc");
        InputFieldAttributes.setColumns(cc, 50);
        ccReport.getFields().add(cc);
        map.addInputFieldGroup(ccReport);
        return map;
    }
    
    @Override
    public Map<String, Object> referenceData(SubmitExpeditedAdverseEventCommand submitCommand) {
        Report report = submitCommand.getReport();
    	submitCommand.refreshReportDeliveries(report);
    	return  super.referenceData( submitCommand);
    }

    @Override
    protected void validate(SubmitExpeditedAdverseEventCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        Report report = command.getReport();
        ReportVersion lastVersion = report.getLastVersion();
        String emailString =lastVersion.getCcEmails();

        if (emailString != null) {
            String[] emails = emailString.split(",");
            for (String email : emails) {
                if (!isEmailValid(email)) {
                    InputField field = fieldGroups.get("ccReport").getFields().get(0);
                    errors.rejectValue(field.getPropertyName(), "SAE_007", new Object[]{field.getDisplayName()},"Not Valid " + field.getDisplayName());
                    break;
                }
            }
        }
    }

    public boolean isEmailValid(String email) {
    	String trimmedEmail = (email != null) ? email.trim() : email;
        return EmailValidator.getInstance().isValid(trimmedEmail);
    }

    @Override
    public void postProcess(HttpServletRequest request, SubmitExpeditedAdverseEventCommand command, Errors errors) {
        log.debug("In postProcess");

        int targetPage = WebUtils.getTargetPage(request);

        if(log.isDebugEnabled()) log.debug("Should process SubmitReportTab:postProcess (back button) ? " + !(targetPage < 2) );
    	if( targetPage < 2) return; //only process if we are moving forward.

    	if(log.isDebugEnabled()) log.debug("Should process SubmitReportTab:postProcess (errors) ? " + !errors.hasErrors() );
        if(errors.hasErrors()) return;

        if(log.isDebugEnabled()) log.debug("Report active ? " + command.getReport().isActive());
        if(log.isDebugEnabled()) log.debug("Report submitted already ? " + command.isSubmissionInprogress());

    	if(command.getReport().isActive() && (!command.isSubmissionInprogress()) ){
        	reportSubmissionService.submitReport(command.getReport());
            command.setSubmissionInprogress(true);
        }
    }
    
    public ReportSubmissionService getReportSubmissionService() {
 	   return reportSubmissionService;
    }
   
    @Required
    public void setReportSubmissionService(ReportSubmissionService reportSubmissionService) {
 	   this.reportSubmissionService = reportSubmissionService;
    }
}
