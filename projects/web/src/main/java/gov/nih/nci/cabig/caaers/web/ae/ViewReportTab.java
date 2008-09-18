package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import org.springframework.beans.BeanWrapper;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
public class ViewReportTab extends AeTab {
    private MessageSource messageSource;

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
        ReportSubmittability reportSubmittability = new ReportSubmittability();
        for (ExpeditedReportSection section : ExpeditedReportSection.values()) {

            if (!section.isAssociatedToBusinessRules()) continue;

            ValidationErrors validationErrors = evaluationService.validateReportingBusinessRules( command.getAeReport(), section);
            for (ValidationError vError : validationErrors.getErrors()) {
                reportSubmittability.addValidityMessage(section, messageSource.getMessage(vError.getCode(), vError.getReplacementVariables(), vError.getMessage(), Locale.getDefault()));
            }
        }

        reportMessages.put(ExpeditedAdverseEventInputCommand.ZERO, reportSubmittability);

        // -- check the report submittability
        for (Report report : command.getAeReport().getReports()) {
            reportMessages.put(report.getId(), evaluationService.isSubmittable(report));
        }
        refdata.put("reportMessages", reportMessages);
        return refdata;
    }

    private void handleWithdrawAction(ExpeditedAdverseEventInputCommand command, String action,String selected) {
        if ("withdraw".equals(action)) {

            for (Report report : command.getAeReport().getReports()) {
                // TODO: there's no chance this actually works -- report.getId() is an Integer and
                // selected is a String
                if (report.getId().equals(selected) && !report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)) {
                    reportRepository.withdrawLastReportVersion(report);
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

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
