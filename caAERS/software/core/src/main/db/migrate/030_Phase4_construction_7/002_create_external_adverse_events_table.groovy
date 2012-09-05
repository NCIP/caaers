class CreateExternalAdverseEventsTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       	createTable("ext_adverse_events") { t ->
            t.addColumn("version_id", "integer", nullable: false)
            t.addColumn("adverse_event_term", "string", nullable: false)
            t.addColumn("attribution", "string", nullable: false)
            t.addColumn("start_date", "date", nullable: false)
            t.addColumn("end_date", "date", nullable: false)
            t.addColumn("verbatim", "string", nullable: false)
            t.addColumn("grade", "integer", nullable: false)
            t.addColumn("external_id", "string", nullable: false)
        }
    }

    void down() {
        dropTable("ext_adverse_events")
    }
}