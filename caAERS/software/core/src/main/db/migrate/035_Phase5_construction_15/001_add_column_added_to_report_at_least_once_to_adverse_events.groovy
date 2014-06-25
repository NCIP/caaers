class AddColumnAddedToReportAtLeastOnceToAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events","added_to_report_at_least_once","boolean");
    }

    void down() {
        dropColumn("adverse_events","added_to_report_at_least_once");
    }
}