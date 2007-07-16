package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.BaseSelectField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin, Priyatam
 */
public abstract class StudyTab extends TabWithFields<Study> {
    // TODO: This should be named after the class, but the existing context instance
    // is named like this.  Alternate TODO: get rid of this bogus class entirely.
    private ConfigProperty configurationProperty;
    protected static final Log log = LogFactory.getLog(StudyTab.class);

   
    public StudyTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configProperty) {
        this.configurationProperty = configProperty;
    }

    protected void addConfigMapToRefdata(Map<String, Object> refdata, String name) {
        refdata.put(name, getConfigurationProperty().getMap().get(name));
    }
    protected Map<Object, Object> collectOptions(List list, 
    		String nameProperty, String valueProperty){
    	Map<Object, Object> options = new LinkedHashMap<Object, Object>();
    	options.put("" , "Please select");
    	options.putAll(BaseSelectField.collectOptions(list,nameProperty, valueProperty));
    	return options;
    }
    protected Map<Object, Object> collectOptionsFromConfig(String configPropertyName,
    		String nameProperty, String valueProperty){
    	return collectOptions((List)configurationProperty.getMap().get(configPropertyName),
    			nameProperty, valueProperty);
    }

	
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(Study command) {
		return new InputFieldGroupMap();
	}
	
	protected List<Lov> collectStudySiteDropdown(Study study){
		ArrayList<Lov> list = new ArrayList<Lov>();
		list.add(new Lov("-1", "Select a site"));
		if(study.getStudySites() != null){
			int i = -1; 
			for(StudySite ss : study.getStudySites()){
				if(ss.getOrganization() != null){
					list.add(new Lov(String.valueOf(++i), ss.getOrganization().getName() + "(" + ss.getRoleCode() + ")" ));
				}
			}
		}
		return list;  
	}
	
}
