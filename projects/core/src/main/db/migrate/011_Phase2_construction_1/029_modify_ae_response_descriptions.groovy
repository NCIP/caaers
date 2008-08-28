class ModifyAEResponseDescriptions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_report_descriptions", "autopsy_performed", "boolean")
        addColumn("ae_report_descriptions", "cause_of_death", "string")
    }

    void down() {
        dropColumn("ae_report_descriptions", "autopsy_performed")
        dropColumn("ae_report_descriptions", "cause_of_death")
    }
}