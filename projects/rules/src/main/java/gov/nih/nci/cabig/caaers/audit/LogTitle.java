package gov.nih.nci.cabig.caaers.audit;

public enum LogTitle {
    RULE_SET_NAME("Rule Set Name"), RULE_SET_TYPE("Rule Set Type"), STUDY_NAME("Study Name"), ORGANIZATION_NAME(
                    "Organization Name"), ORGANIZATION_ROLE("Organization Role"), TIME_OF_EXECUTION(
                    "Time Of Execution"), BASIC_INFO("Basic Information"), ASSERTED_OBJECTS(
                    "Asserted Objects"), STUDY("Study"), ADVERSE_EVENT("Adverse Event"), EXECUTION_STATUS(
                    "Execution Status"), RULE_NAME("Rule Name"), CONDITION_MET("Condition Met ?"), FIRED(
                    "Fired ?"), ID("Id"), GRADE("Grade"), TERM("Term"), CATEGORY("Category"), HOSPITALIZATION(
                    "Hospitalization"), EXPECTED("Expected"), ATTRRIBUTION("Attribution"), PHASE(
                    "Phase"), STUDY_AGENT("Study Agent"), INVESTIGATIONAL_NEW_DRUG_INDICATOR(
                    "investigationalNewDrugIndicator");

    private String title;

    LogTitle(String name) {
        title = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
