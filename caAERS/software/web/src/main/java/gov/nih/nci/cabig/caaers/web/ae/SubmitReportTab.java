package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
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
public class SubmitReportTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {

	protected final Log log = LogFactory.getLog(getClass());
	protected ReportSubmissionService reportSubmissionService;
	
    public SubmitReportTab() {
        super("Submission", "Recipients", "ae/submitReport");

    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        String reportIndex = ((SubmitExpeditedAdverseEventCommand) command).getReportIndex();
        if (reportIndex == null) {
            throw new CaaersSystemException("Report Index Not Defined");
        }
        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup ccReport = new DefaultInputFieldGroup("ccReport");
        InputField cc = InputFieldFactory.createTextArea("aeReport.reports[" + reportIndex
                        + "].lastVersion.ccEmails", "Cc");
        InputFieldAttributes.setColumns(cc, 50);
        ccReport.getFields().add(cc);
        map.addInputFieldGroup(ccReport);
        return map;
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        String reportIndex = ((SubmitExpeditedAdverseEventCommand) command).getReportIndex();
        Report report = command.getAeReport().getReports().get(Integer.parseInt(reportIndex));
        
        if(!report.isActive()){
			errors.reject("SAE_032", new Object[]{report.getStatus().getDisplayName()},
					"Cannot submit this report, as it is already submitted/withdrawn/amended/replaced");
		}
        
        ReportVersion lastVersion = report.getLastVersion();
        
        String emailString =lastVersion.getCcEmails();

        if (emailString != null) {
            String[] emails = emailString.split(",");

            for (String email : emails) {

                if (!isEmailValid(email)) {
                    InputField field = fieldGroups.get("ccReport").getFields().get(0);
                    errors.rejectValue(field.getPropertyName(), "SAE_007", new Object[]{field.getDisplayName()},"Not Valid "
                                    + field.getDisplayName());
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
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand cmd, Errors errors) {
    	
    	if(cmd.getNextPage() < 2) return; //only process if we are moving forward.
    	
    	log.debug("In postProcess");
        SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) cmd;
        Integer reportIndex = Integer.valueOf(command.getReportIndex());

        log.debug("Report Index :" + reportIndex.intValue());
        ExpeditedAdverseEventReport aeReport = command.getAeReport();
        Report report = aeReport.getReports().get(((int) reportIndex));
        if(!command.getReportSubmitted()){
        	reportSubmissionService.submitReport(report);
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
