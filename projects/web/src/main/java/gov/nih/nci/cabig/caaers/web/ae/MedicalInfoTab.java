package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.BasePropertyInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.BaseSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

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
        return BaseSelectField.collectOptions(lov, "code", "desc", blankValue);
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        BasePropertyInputFieldGroup participant
            = new BasePropertyInputFieldGroup("participant", "aeReport.participantHistory");
        participant
            .addField(new DefaultTextField("height", "Height"))
            .addField(new DefaultSelectField("heightUnitOfMeasure", "Height units", false,
                optionsFromConfigurationProperty("heightUnitsRefData")))
            .addField(new DefaultTextField("weight", "Weight"))
            .addField(new DefaultSelectField("weightUnitOfMeasure", "Weight units", false,
                optionsFromConfigurationProperty("weightUnitsRefData")))
            .addField(new DefaultSelectField("baselinePerformanceStatus", "Baseline performance", false,
                optionsFromConfigurationProperty("bpsRefData", "Please select")))
            ;

        BasePropertyInputFieldGroup disease
            = new BasePropertyInputFieldGroup("disease", "aeReport.diseaseHistory");
        Map<Object, Object> ctepStudyDiseaseOptions = BaseSelectField.collectOptions(
            command.getStudy().getCtepStudyDiseases(), "id", "term.term", ""
        );
        DefaultSelectField ctepStudyDisease = new DefaultSelectField("ctepStudyDisease", "Disease from study", false,
            ctepStudyDiseaseOptions);
        ctepStudyDisease.getAttributes().put(InputField.DETAILS,
            "If the correct disease isn't listed above, enter it in other.");
        AutocompleterField diseaseSite = new AutocompleterField("anatomicSite", "Primary disease site", false);
        diseaseSite.getAttributes().put(InputField.DETAILS,
            "If the correct site isn't in the autocompleter above, please enter it in other.");
        disease
            .addField(ctepStudyDisease)
            .addField(new DefaultTextField("otherPrimaryDiseaseCode", "Other disease"))
            .addField(diseaseSite)
            .addField(new DefaultTextField("otherPrimaryDiseaseSiteCode", "Other primary disease site"))
            .addField(new DefaultDateField("dateOfInitialPathologicDiagnosis", "Diagnosis date", false))
            ;

        RepeatingFieldGroupFactory fieldFactory = new RepeatingFieldGroupFactory("metastatic", "aeReport.diseaseHistory.metastaticDiseaseSite");
        fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Metastatic disease site " + index;
            }
        });
        fieldFactory.addField(new AutocompleterField("anatomicSite", "Site Name", false));
        fieldFactory.addField(new DefaultTextField("otherMetastaticDiseaseSite", "Other Site", false));

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(participant);
        map.addInputFieldGroup(disease);
        map.addRepeatingFieldGroupFactory(fieldFactory,
            command.getAeReport().getDiseaseHistory().getMetastaticDiseaseSite().size());
        return map;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

}
