class ModifyAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events","event_time_hour", "integer")
      	addColumn("adverse_events","event_time_minute", "integer")
        addColumn("adverse_events", "event_location", "string")
    }

    void down() {
        dropColumn("ae_report_descriptions", "event_time_hour")
        dropColumn("ae_report_descriptions", "event_time_minute")
        dropColumn("ae_report_descriptions", "event_location")
    }
}