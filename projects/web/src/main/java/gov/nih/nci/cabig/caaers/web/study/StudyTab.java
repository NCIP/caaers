package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin, Priyatam
 */
public abstract class StudyTab extends Tab<Study> {
    // TODO: This should be named after the class, but the existing context instance
    // is named like this.  Alternate TODO: get rid of this bogus class entirely.
    private ConfigProperty configurationProperty;
    protected static final Log log = LogFactory.getLog(EditStudyController.class);

    public StudyTab() { }
   
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
    
}
