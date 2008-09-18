package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.utils.WebUtils.collectOptions;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createDateField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createSelectField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextArea;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextField;
import gov.nih.nci.cabig.caaers.domain.RadiationAdministration;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Krikor Krumlian
 */
public class RadiationInterventionTab extends AeTab {

    private ConfigProperty configurationProperty;

    public RadiationInterventionTab() {
        super("Radiation Intervention", ExpeditedReportSection.RADIATION_INTERVENTION_SECTION
                        .getDisplayName(), "ae/radiationIntervention");
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refData = super.referenceData(request, command);

        String code = command.getAeReport().getTreatmentInformation().getTreatmentAssignment() != null ? command
                        .getAeReport().getTreatmentInformation().getTreatmentAssignment().getCode()
                        : null;

        String description = code != null ? command.getAeReport().getTreatmentInformation()
                        .getTreatmentAssignmentDescription() : command.getAeReport()
                        .getTreatmentInformation().getTreatmentDescription();

        refData.put("code", code);
        refData.put("description", description);

        return refData;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {

        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(collectOptions(Arrays.asList(RadiationAdministration.values()), null,
                        "displayName"));

        InputField doseUOMField = InputFieldFactory.createSelectField("dosageUnit",
                        "Unit of measure", false, WebUtils.collectOptions(
                                        configurationProperty.getMap().get(
                                                        "radiationDoseUMORefData"), "code", "desc",
                                        "Please Select"));
        InputField fractionNumberField = createTextField("fractionNumber",
                        "Schedule number of fractions", false);
        fractionNumberField.getAttributes().put(InputField.HELP,
                        "ae.radiationIntervention.aeReport.radiationInterventions.fractionNumber");
        creator.createRepeatingFieldGroup("radiationIntervention", "radiationInterventions",
                        new SimpleNumericDisplayNameCreator("Radiation"), createSelectField(
                                        "administration", "Type of radiation administration",
                                        false, statusOpts), 
                                        createTextField("dosage", "Dosage",false), 
                                        doseUOMField, createDateField("lastTreatmentDate","Date of last treatment", false), fractionNumberField,
                        createTextField("daysElapsed", " Number of elapsed days", false),
                        // createTextField("adjustment", "Adjustment", false)
                        createSelectField("adjustment", "Adjustment", false, WebUtils
                                        .collectOptions(configurationProperty.getMap().get(
                                                        "radiationAdjustmentRefData"), "code",
                                                        "desc", "Please Select")));
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.RADIATION_INTERVENTION_SECTION};
    }
}
