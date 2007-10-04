package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class CheckpointTab extends AeTab {
    private static final Log log = LogFactory.getLog(CheckpointTab.class);

    private EvaluationService evaluationService;
    
    public CheckpointTab() {
        super("Is expedited reporting necessary?", "Select Report", "ae/checkpoint");
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.CHECKPOINT_SECTION;
    }

    @Override
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroup optional = new DefaultInputFieldGroup("optionalReports");
        for (ReportDefinition reportDefinition : command.getOptionalReportDefinitionsMap().keySet()) {
            optional.getFields().add(InputFieldFactory.createCheckboxField(
                "optionalReportDefinitionsMap[" + reportDefinition.getId() + ']',
                reportDefinition.getName() + " (" + reportDefinition.getOrganization().getName() + ')'
            ));
        }

        return InputFieldGroupMap.create(optional);
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
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors ) {
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

    	//the list of report definitions to instantiate
    	List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();

        for (ReportDefinition def : command.getOptionalReportDefinitionsMap().keySet()) {
            if (optionalReportSelected(command, def) && (findReportWithDefinition(command.getAeReport(), def) == null)) {
                reportDefs.add(def); //  addOptionalReport(command, command.getAeReport(), def);
            } else {
                removeOptionalReport(command.getAeReport(), def);
            }
        }
        if(reportDefs.size() > 0){
        	evaluationService.addOptionalReports(command.getAeReport(), reportDefs);
        }
        if (command.getAeReport().getReports().size() > 0) {
            command.save();
        }
        //find the mandatory sections.
        if (command.getMandatoryProperties() == null) {
            command.setMandatorySections(evaluationService.mandatorySections(command.getAeReport()));
            command.refreshMandatoryProperties();
        }
    }

    private boolean optionalReportSelected(ExpeditedAdverseEventInputCommand command, ReportDefinition def) {
        Boolean val = command.getOptionalReportDefinitionsMap().get(def);
        return val == null ? false : val;
    }

    private void removeOptionalReport(ExpeditedAdverseEventReport aeReport, ReportDefinition def) {
        // TODO: we're going to need a service method for this, too
        Report existing = findReportWithDefinition(aeReport, def);
        if (existing != null && !existing.isRequired()) {
            aeReport.getReports().remove(existing);
            reportService.deleteReport(existing); //remove the existing report from ReportDefinition
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
}
