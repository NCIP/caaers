package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.BasePropertyInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.util.List;
import java.util.Map;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
public class MedicalInfoTab extends AeTab {
    private ConfigProperty configurationProperty;

    public MedicalInfoTab() {
        super("Medical info", "Patient Details", "ae/medical");
    }

    // TODO: configurationProperty and all things associated with it are abhorrent
    private Map<Object, Object> optionsFromConfigurationProperty(String propName) {
        return optionsFromConfigurationProperty(propName, null);
    }

    private Map<Object, Object> optionsFromConfigurationProperty(String propName, String blankValue) {
        List<Lov> lov = configurationProperty.getMap().get(propName);
        if (lov == null) throw new CaaersSystemException("No LOV for " + propName);
        return InputFieldFactory.collectOptions(lov, "code", "desc", blankValue);
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        BasePropertyInputFieldGroup participant
            = new BasePropertyInputFieldGroup("participant", "aeReport.participantHistory");
        participant
            .addField(createParticipantMeasureField("height", "Height",
                optionsFromConfigurationProperty("heightUnitsRefData")))
            .addField(createParticipantMeasureField("weight", "Weight",
                optionsFromConfigurationProperty("weightUnitsRefData")))
            .addField(InputFieldFactory.createSelectField("baselinePerformanceStatus", "Baseline performance", false,
                optionsFromConfigurationProperty("bpsRefData", "Please select")))
            ;

        BasePropertyInputFieldGroup disease
            = new BasePropertyInputFieldGroup("disease", "aeReport.diseaseHistory");
        Map<Object, Object> ctepStudyDiseaseOptions = InputFieldFactory.collectOptions(
            command.getStudy().getCtepStudyDiseases(), "id", "term.term", ""
        );
        InputField ctepStudyDisease = InputFieldFactory.createSelectField("ctepStudyDisease", "Disease name", false,
            ctepStudyDiseaseOptions);
        InputFieldAttributes.setDetails(ctepStudyDisease, "If the correct disease is not listed in the drop down list, type the appropriate disease name in the Other (disease) field below.");
        InputField diseaseSite = InputFieldFactory.createAutocompleterField("codedPrimaryDiseaseSite", "Primary site of disease", false);
        InputFieldAttributes.setDetails(diseaseSite, "If the appropriate site cannot be found in the list above, type the primary site of the disease in the Other (site of primary disease) field below.");
        InputField otherDiseaseField = InputFieldFactory.createTextField("otherPrimaryDisease", "Other (disease)");
        InputFieldAttributes.setDetails(otherDiseaseField, "If this is a prevention trial, and disease is not applicable, enter Disease Not Applicable.");
        InputField diganosisDateField = InputFieldFactory.createDateField("diagnosisDate", "Date of initial diagnosis", false);
        InputFieldAttributes.setDetails(diganosisDateField, "If known, enter the date of the initial diagnosis.");
        disease
            .addField(ctepStudyDisease)
            .addField(otherDiseaseField)
            .addField(diseaseSite)
            .addField(InputFieldFactory.createTextField("otherPrimaryDiseaseSite", "Other (site of primary disease)"))
            .addField(diganosisDateField)
            ;

        RepeatingFieldGroupFactory fieldFactory = new RepeatingFieldGroupFactory("metastatic", "aeReport.diseaseHistory.metastaticDiseaseSites");
        fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Metastatic disease site " + (index + 1);
            }
        });
        InputField codedSiteField = InputFieldFactory.createAutocompleterField("codedSite", "Site Name", false);
        InputFieldAttributes.setDetails(codedSiteField, "If the appropriate site is not listed, type the specific site in the <strong>Other(Site of Metastatic Disease)</strong> field");
        fieldFactory.addField(codedSiteField);

        fieldFactory.addField(InputFieldFactory.createTextField("otherSite", "Other(Site of metastatic disease)", false));

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(participant);
        map.addInputFieldGroup(disease);
        map.addRepeatingFieldGroupFactory(fieldFactory,
            command.getAeReport().getDiseaseHistory().getMetastaticDiseaseSites().size());
        return map;
    }

    private CompositeField createParticipantMeasureField(String baseName, String baseDisplayName, Map<Object, Object> unitOptions) {
        return new CompositeField(baseName,
            new DefaultInputFieldGroup(null, baseDisplayName)
                .addField(InputFieldFactory.createTextField("quantity", ""))
                .addField(InputFieldFactory.createSelectField("unit", "units", false, unitOptions))
        );
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.MEDICAL_INFO_SCECTION;
    }

}
