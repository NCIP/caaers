package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultCheckboxField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class CheckpointTab extends AeTab {
    private EvaluationService evaluationService;
    private ReportService reportService;

    public CheckpointTab() {
        super("Is expedited reporting necessary?", "SAE?", "ae/checkpoint");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroup optional = new DefaultInputFieldGroup("optionalReports");
        for (ReportDefinition reportDefinition : command.getOptionalReportDefinitionsMap().keySet()) {
            optional.getFields().add(new DefaultCheckboxField(
                "optionalReportDefinitionsMap[" + reportDefinition.getId() + ']',
                reportDefinition.getName()
            ));
        }

        return Collections.singletonMap(optional.getName(), optional);
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        evaluationService.addRequiredReports(command.getAeReport());
        command.setOptionalReportDefinitions(createOptionalReportDefinitionsList(command));
    }

    private List<ReportDefinition> createOptionalReportDefinitionsList(ExpeditedAdverseEventInputCommand command) {
        List<ReportDefinition> all = evaluationService.applicableReportDefinitions(command.getAssignment());
        for (Report report : command.getAeReport().getReports()) {
            if (report.isRequired()) {
                all.remove(report.getReportDefinition());
            }
        }
        return all;
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        boolean anyReports = command.getAeReport().getReports().size() > 0;
        for (Boolean selected : command.getOptionalReportDefinitionsMap().values()) {
            anyReports |= selected;
        }
        if (!anyReports) {
            errors.reject("AT_LEAST_ONE_REPORT", "At least one expedited report must be selected to proceed");
        }
    }

    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Errors errors) {
        for (ReportDefinition def : command.getOptionalReportDefinitionsMap().keySet()) {
            if (command.getOptionalReportDefinitionsMap().get(def)) {
                addOptionalReport(command.getAeReport(), def);
            } else {
                removeOptionalReport(command.getAeReport(), def);
            }
        }
        if (command.getAeReport().getReports().size() > 0) {
            command.save();
        }
    }

    private void addOptionalReport(ExpeditedAdverseEventReport aeReport, ReportDefinition def) {
        // TODO: this only creates the report object -- need a unified way to set up the notifications, etc.
        if (findReportWithDefinition(aeReport, def) == null) {
            reportService.createReport(def, aeReport);
        }
    }

    private void removeOptionalReport(ExpeditedAdverseEventReport aeReport, ReportDefinition def) {
        Report existing = findReportWithDefinition(aeReport, def);
        if (existing != null && !existing.isRequired()) {
            aeReport.getReports().remove(existing);
        }
    }

    private Report findReportWithDefinition(ExpeditedAdverseEventReport aeReport, ReportDefinition def) {
        for (Report report : aeReport.getReports()) {
            if (report.getReportDefinition().equals(def)) return report;
        }
        return null;
    }

    ////// CONFIGURATION

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }
}
