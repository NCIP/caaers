class AlterReportDescription extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropColumn("ae_report_descriptions", "event_abate")
        dropColumn("ae_report_descriptions", "event_reappear")
        addColumn("ae_report_descriptions", "event_abate", "integer")
        addColumn("ae_report_descriptions", "event_reappear", "integer")
    }

    void down() {
        dropColumn("ae_report_descriptions", "event_abate")
        dropColumn("ae_report_descriptions", "event_reappear")
    }
}