package gov.nih.nci.cabig.caaers.rules.common;

public enum RuleLevel {
    Sponsor("Sponsor", "Rules for Sponsor"),
    Institution("Institution", "Rules for Institution"),
    SponsorDefinedStudy("SponsorDefinedStudy", "Rules for Sponsor Defined Study"),
    InstitutionDefinedStudy( "InstitutionDefinedStudy", "Rules for Institution Defined Study");

    private String name;
    private String desc;

    RuleLevel(String name, String desc) {
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
