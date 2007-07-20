package gov.nih.nci.cabig.caaers;

/**
 * @author Rhett Sutphin
 */
public enum CaaersUseCase /* implements UseCase */ {
    CREATE_STUDY(1, 1, "Create Study for AE Data Entry"),
    CREATE_PARTICIPANT(1, 2, "Create Subject"),
    ASSIGN_PARTICIPANT(1, 3, "Assign Subject to a Study"),
    STUDY_ABSTRACTION(1, 4, "Study Abstraction -- Sponsors, Committees, and Organizations"),
    CREATE_EXPEDITED_REPORT(1, 5, "Create Expedited AE Report"),
    CREATE_ROUTINE_REPORT(1, 6, "Create Routine AE Report"),
    AE_DATA_COLLECTION(1, 7, "AE Data Collection"),
    SEARCH_AE_ENTRY(1, 8, "Search AE Entry"),
    CREATE_REPORT_RULES(1, 9, "Create AE Notification and Report Ruleset"),
    CREATE_NOTIFICATION_RULES(1, 10, "Configure Messages, Timing and Escalation Rules for Notifications"),
    CREATE_SOLICITED_AE_FORM(1, 11, "Create Solicited AE Form"),
    ENTER_SOLICITED_AE_DATA(1, 12, "Enter Solicited AE Data"),

    IMPORT_STUDIES(2, 1, "Import Study Data"),
    IMPORT_PARTICIPANTS(2, 2, "Import Patient Data"),
    EXPORT_AE_DATA(2, 3, "Export of AE Information into Local CT Databases for Future Aggregation and Analysis"),

    MAPPING_VOCAB(3, 1, "Mapping Vocabularies and Coding Systems"),

    CREATE_REPORT_FORMAT(4, 1, "Create Report Formats"),
    GENERATE_REPORT_FORM(4, 2, "Generate Report Form"),

    PUSH_AE_TO_PSC(5, 1, "Push AE Notifications to Patient Study Calendar"),
    RECIEVE_RESULTS_FROM_LABVIEWER(5, 2, "Create Expedited Report with Data from CTOM Lab Viewer"),
    
    CREATE_SPONSOR_RULESET(1, 1, "Create Rule Set for Sponsor"),
    CREATE_INSTITUTION_RULESET(1, 1, "Create Rule Set for Institution"),
    CREATE_SPONSOR_DEFINED_STUDY_RULESET(1, 1, "Create Rule Set for Sponsor defined Study"),
    CREATE_INSTITUTION_DEFINED_STUDY_RULESET(1, 1, "Create Rule Set for Institution defined Study"),
    SAVE_SPONSOR_RULES(1, 1, "Save Rules For Sponsor"),
    SAVE_INSTITUTION_RULES(1, 1, "Save Rules For Institution"),
    SAVE_SPONSOR_DEFINED_STUDY_RULES(1, 1, "Save Rules For Sponsor defined Study"),
    SAVE_INSTITUTION_DEFINED_STUDY_RULES(1, 1, "Save Rules For Institution defined Study"),
    
    DELPLOY_RULESET(1, 1, "Deploy Rule Set"),
    
    EVALUATE_AE_ASSESMENT_RULES(1, 1, "Evaluate Serious Adverse Event"),
    EVALUATE_SAE_REPORTING_RULES(1, 1, "Evaluate SAE Reporting Schedule")
    ;
    
    
    
    private int major;
    private int minor;
    private String title;

    CaaersUseCase(int major, int minor, String title) {
        this.major = major;
        this.minor = minor;
        this.title = title;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public String getTitle() {
        return title;
    }
}
