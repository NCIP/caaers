package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.*;

public class ReportTypeCommand {

    private ConfigProperty cp;
    private String code;
    private String name;
    private String description;

    public ReportTypeCommand() {
        if (cp == null) { 
            cp = new ConfigProperty();
            cp.setConfigType(ConfigPropertyType.REPORT_TYPE);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ConfigProperty getCp() {
        return cp;
    }

    public void setCp(ConfigProperty cp) {
        this.cp = cp;
    }
}