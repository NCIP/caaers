package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

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
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        return new InputFieldGroupMap();
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
    
    @Override
    public boolean isAllowDirtyForward() {
        return false;
    }
    
    public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}

}
