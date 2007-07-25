package gov.nih.nci.cabig.caaers.web.ae;

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
        super("Medical info", "Medical", "ae/medical");
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
        InputField ctepStudyDisease = InputFieldFactory.createSelectField("ctepStudyDisease", "Disease from study", false,
            ctepStudyDiseaseOptions);
        InputFieldAttributes.setDetails(ctepStudyDisease, "If the correct disease isn't listed above, enter it in other.");
        InputField diseaseSite = InputFieldFactory.createAutocompleterField("codedPrimaryDiseaseSite", "Primary disease site", false);
        InputFieldAttributes.setDetails(diseaseSite, "If the correct site isn't in the autocompleter above, please enter it in other.");
        disease
            .addField(ctepStudyDisease)
            .addField(InputFieldFactory.createTextField("otherPrimaryDisease", "Other disease"))
            .addField(diseaseSite)
            .addField(InputFieldFactory.createTextField("otherPrimaryDiseaseSite", "Other primary disease site"))
            .addField(InputFieldFactory.createDateField("diagnosisDate", "Diagnosis date", false))
            ;

        RepeatingFieldGroupFactory fieldFactory = new RepeatingFieldGroupFactory("metastatic", "aeReport.diseaseHistory.metastaticDiseaseSites");
        fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Metastatic disease site " + (index + 1);
            }
        });
        fieldFactory.addField(InputFieldFactory.createAutocompleterField("codedSite", "Site Name", false));
        fieldFactory.addField(InputFieldFactory.createTextField("otherSite", "Other Site", false));

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

}
