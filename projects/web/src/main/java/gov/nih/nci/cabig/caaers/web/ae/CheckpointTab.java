package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import org.apache.commons.collections15.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class CheckpointTab extends AeTab {
    private static final Log log = LogFactory.getLog(CheckpointTab.class);

    public CheckpointTab() {
        super("Is expedited reporting necessary?", ExpeditedReportSection.CHECKPOINT_SECTION
                        .getDisplayName(), "ae/checkpoint");
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.CHECKPOINT_SECTION;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        InputFieldGroup optional = new DefaultInputFieldGroup("optionalReports");
        for (ReportDefinition reportDefinition : command.getOptionalReportDefinitionsMap().keySet()) {
            optional.getFields().add(
                            InputFieldFactory.createCheckboxField("optionalReportDefinitionsMap["
                                            + reportDefinition.getId() + ']', reportDefinition
                                            .getName()
                                            + " ("
                                            + reportDefinition.getOrganization().getName()
                                            + ')'));
        }

        creator.addUnprocessedFieldGroup(optional);
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {

        // evalutate available report definitions per session.
        if (command.getAllReportDefinitions() == null
                        || command.getAllReportDefinitions().isEmpty()) {
            command.setAllReportDefinitions(evaluationService.applicableReportDefinitions(command
                            .getAssignment()));
        }

        // identify the report definitions mandated by Rules engine
        if (command.getRequiredReportDeifnitions().isEmpty()) {
            command.setRequiredReportDefinition(evaluationService
                            .findRequiredReportDefinitions(command.getAeReport()));
        }

        // already AE report is saved.
        if (command.getAeReport().getId() != null) {
            // set up selected reports
            command.refreshSelectedReportDefinitionsMap(command.getInstantiatedReportDefinitions());
            // set up the optional reports
            command.setOptionalReportDefinitions(createOptionalReportDefinitionsList(command));
        } else {
            // set up the optional reports
            command.setOptionalReportDefinitions(createOptionalReportDefinitionsList(command));
            // new, so no reports are associated with this yet.
            command.setSelectedReportDefinitions(command.getRequiredReportDeifnitions());
        }
    }

    private List<ReportDefinition> createOptionalReportDefinitionsList(
                    ExpeditedAdverseEventInputCommand command) {
        if (command.getSelectedReportDefinitions() == null) return command
                        .getAllReportDefinitions();

        return ListUtils.subtract(command.getAllReportDefinitions(), command
                        .getSelectedReportDefinitions());
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {

        // only do validate, if we are moving forward or if the AE report is already persistent
        if ((command.getNextPage() >= getNumber()) || command.getAeReport().getId() != null) {

            boolean anyReports = command.getAeReport().getReports().size() > 0;
            for (ReportDefinition def : command.getOptionalReportDefinitionsMap().keySet()) {
                anyReports |= reportSelected(command, def);
            }
            if (!anyReports) {
                errors.reject("AT_LEAST_ONE_REPORT",
                                "At least one expedited report must be selected to proceed");
            }

        }
    }

    @Override
    public void onBind(HttpServletRequest request, ExpeditedAdverseEventInputCommand command,
                    Errors errors) {
        super.onBind(request, command, errors);
        // explicitly call setSelectedReportDefinitionNames
        command.setSelectedReportDefinitionNames(request
                        .getParameter("selectedReportDefinitionNames"));
    }

    @SuppressWarnings("deprecation")
    @Override
    /**
     * We do the following things here 
     * 	1. Find the newly checked report definitions 
     *  2. Remove the
     * unselected report definitions
     *  3. Create the reports (by calling evaluation service)
     *  4. Save the AEReport
     *  5. Fetch the mandatorySections based on the reports selected 
     *  6. Pre-instantiate the mandatory section's repeating fields (biz rule) 
     *  7. Refresh the mandatory fields map.
     */
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command,
                    Errors errors) {

        // only do postProcess, if we are moving forward or if the AE report is already persistent
        if ((command.getNextPage() >= getNumber()) || command.getAeReport().getId() != null) {

            List<ReportDefinition> newlySelectedDefs = newlySelectedReportDefinitions(command);
            removeUnselectedReports(command);

            if (newlySelectedDefs != null) {
                evaluationService.addOptionalReports(command.getAeReport(), newlySelectedDefs);
            }

            if (command.getAeReport().getReports().size() > 0) {
                command.save();
            }
            // find the new mandatory sections
            command
                            .setMandatorySections(evaluationService.mandatorySections(command
                                            .getAeReport()));

            // pre-init the mandatory section fields
            command.initializeMandatorySectionFields(getExpeditedReportTree());

            // refresh the mandatory fields
            command.refreshMandatoryProperties();
        }

    }

    private List<ReportDefinition> newlySelectedReportDefinitions(
                    ExpeditedAdverseEventInputCommand command) {
        List<ReportDefinition> selectedReportDefs = command.getSelectedReportDefinitions();
        List<ReportDefinition> instantiatedReportDefs = command.getInstantiatedReportDefinitions();
        List<ReportDefinition> difference = ListUtils.subtract(selectedReportDefs,
                        instantiatedReportDefs);
        return difference;
    }

    private void removeUnselectedReports(ExpeditedAdverseEventInputCommand command) {
        List<Report> reports = command.getAeReport().getReports();
        for (Report report : reports) {
            if (report.getStatus() == ReportStatus.WITHDRAWN) continue;
            if (!reportSelected(command, report.getReportDefinition())) {
                reportRepository.deleteReport(report);
            }
        }
    }

    private boolean reportSelected(ExpeditedAdverseEventInputCommand command, ReportDefinition def) {
        Boolean val = command.getOptionalReportDefinitionsMap().get(def);
        return val == null ? false : val;
    }

}
