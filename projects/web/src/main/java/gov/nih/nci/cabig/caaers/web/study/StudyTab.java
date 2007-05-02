package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public abstract class StudyTab extends Tab<Study> {
    // TODO: This should be named after the class, but the existing context instance
    // is named like this.  Alternate TODO: get rid of this bogus class entirely.
    private ConfigProperty configurationProperty;

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

    // TODO: replace this with something else
    protected static List<StringBean> getBooleanList() {
        List<StringBean> col = new ArrayList<StringBean>();
        col.add(new StringBean("YES"));
        col.add(new StringBean("NO"));
        return col;
    }

    private static class StringBean {
        String str;

        StringBean(String str) {
            this.str = str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }
}
