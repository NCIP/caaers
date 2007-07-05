package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Kulasekaran
 */
public class MedicalInfoTab extends AeTab {

    private ConfigProperty configurationProperty;

    public MedicalInfoTab() {
        super("Medical info", "Medical", "ae/medical");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {

        RepeatingFieldGroupFactory fieldFactory = new RepeatingFieldGroupFactory("metastatic", "aeReport.diseaseHistory.metastaticDiseaseSite");
        fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Site of Metastatic Diseases";
            }
        });
        fieldFactory.addField(new AutocompleterField("anatomicSite", "Site Name", false));
        fieldFactory.addField(new DefaultTextField("otherMetastaticDiseaseSite", "Other Site", false));

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addRepeatingFieldGroupFactory(fieldFactory, 1);
        return map;
    }

    @Override
    public Map<String, Object> referenceData() {
        Map <String, List<Lov>> configMap = configurationProperty.getMap();
        Map<String, Object> refdata = super.referenceData();
        refdata.put("heightUnitsRefData", configMap.get("heightUnitsRefData"));
        refdata.put("weightUnitsRefData", configMap.get("weightUnitsRefData"));
        refdata.put("bpsRefData", configMap.get("bpsRefData"));
        return refdata;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

}
