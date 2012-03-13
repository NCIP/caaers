package gov.nih.nci.cabig.caaers.rules.common;

public enum RuleType {

    REPORT_SCHEDULING_RULES("SAE Reporting Rules", "The rules regarding identifying the reporting periods"),
    MANDATORY_SECTIONS_RULES("Mandatory Sections Rules", "The rules regarding identifying the mandatory sections"),
    FIELD_LEVEL_RULES("Field Rules" , "The rules regarding requiredness of UI fields");

    private String name;
    private String desc;
    private String packageName;

    RuleType(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.packageName = "gov.nih.nci.cabig.caaers.rules";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public String getPackageName() {
        return packageName;
    }

    public static RuleType getByName(String name){
        for(RuleType t : values()){
            if(t.getName().equals(name)) return t;
        }
        return null;
    }

}
