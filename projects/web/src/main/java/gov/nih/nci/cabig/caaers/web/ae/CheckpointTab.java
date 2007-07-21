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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Rhett Sutphin
 */
public class CheckpointTab extends AeTab {
    private static final Log log = LogFactory.getLog(CheckpointTab.class);

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
        if (log.isDebugEnabled()) {
            log.debug("Applicable report defs: " + all);
        }
        for (Report report : command.getAeReport().getReports()) {
            if (report.isRequired()) {
                boolean removed = all.remove(report.getReportDefinition());
                if (log.isDebugEnabled()) {
                    if (removed) {
                        log.debug("  Removed " + report.getReportDefinition() + " from optional list because it is required");
                    } else {
                        log.debug("  Report def " + report.getReportDefinition()
                            + " is in the EAER, but is not applicable according to "
                            + evaluationService.getClass().getName());
                    }
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("Optional report defs: " + all);
        }
        return all;
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        boolean anyReports = command.getAeReport().getReports().size() > 0;
        for (ReportDefinition def : command.getOptionalReportDefinitionsMap().keySet()) {
            anyReports |= optionalReportSelected(command, def);
        }
        if (!anyReports) {
            errors.reject("AT_LEAST_ONE_REPORT", "At least one expedited report must be selected to proceed");
        }
    }

    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Errors errors) {
        for (ReportDefinition def : command.getOptionalReportDefinitionsMap().keySet()) {
            if (optionalReportSelected(command, def)) {
                addOptionalReport(command.getAeReport(), def);
            } else {
                removeOptionalReport(command.getAeReport(), def);
            }
        }
        if (command.getAeReport().getReports().size() > 0) {
            command.save();
        }
    }

    private boolean optionalReportSelected(ExpeditedAdverseEventInputCommand command, ReportDefinition def) {
        Boolean val = command.getOptionalReportDefinitionsMap().get(def);
        return val == null ? false : val;
    }

    private void addOptionalReport(ExpeditedAdverseEventReport aeReport, ReportDefinition def) {
        if (findReportWithDefinition(aeReport, def) == null) {
            reportService.createReport(def, aeReport);
        }
    }

    private void removeOptionalReport(ExpeditedAdverseEventReport aeReport, ReportDefinition def) {
        // TODO: we're going to need a service method for this, too
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
