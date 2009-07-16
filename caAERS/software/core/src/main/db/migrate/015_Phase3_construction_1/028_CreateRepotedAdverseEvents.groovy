class CreateReportedAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
        createTable("reported_adverse_events") { t ->
        	t.addColumn("grid_id", "string")
        	t.addVersionColumn();
        	t.addColumn("report_version_id", "integer", nullable: false)
        	t.addColumn("adverse_event_id", "integer", nullable: false)
        }
    }

    void down() {
        dropTable("reported_adverse_events")
    }
}