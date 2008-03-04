package gov.nih.nci.cabig.caaers.rules.common;

public enum RuleType {

    AE_ASSESMENT_RULES("AE Assesment Rules", "Rules regarding adverse event assesments"), REPORT_SCHEDULING_RULES(
                    "SAE Reporting Rules", "The rules regarding identifying the reporting periods"), MANDATORY_SECTIONS_RULES(
                    "Mandatory Sections Rules",
                    "The rules regarding identifying the mandatory sections");

    private String name;

    private String desc;

    RuleType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

}
