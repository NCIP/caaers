class ModifyAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events","event_time_hour", "integer")
      	addColumn("adverse_events","event_time_minute", "integer")
      	addColumn("adverse_events","event_time_zone", "integer")
        addColumn("adverse_events", "event_location", "string")
        execute("update adverse_events set event_time_zone = 0");
    }

    void down() {
        dropColumn("adverse_events", "event_time_hour")
        dropColumn("adverse_events", "event_time_minute")
        dropColumn("adverse_events", "event_time_zone")
        dropColumn("adverse_events", "event_location")
    }
}