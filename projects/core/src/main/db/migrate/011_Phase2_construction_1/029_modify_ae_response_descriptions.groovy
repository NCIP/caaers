class ModifyAEResponseDescriptions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_report_descriptions", "event_approximate_time", "date")
        addColumn("ae_report_descriptions", "event_location", "string")
        addColumn("ae_report_descriptions", "autopsy_performed", "boolean")
        addColumn("ae_report_descriptions", "cause_of_death", "string")
    }

    void down() {
        dropColumn("ae_report_descriptions", "event_approximate_time")
        dropColumn("ae_report_descriptions", "event_location")
        dropColumn("ae_report_descriptions", "autopsy_performed")
        dropColumn("ae_report_descriptions", "cause_of_death")
    }
}