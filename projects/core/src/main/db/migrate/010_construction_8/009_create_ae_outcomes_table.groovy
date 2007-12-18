class CreateAeOutcomeTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        
        createTable("outcomes") { t ->
        	t.addColumn("incident_date", "date")
        	t.addColumn("outcome_type_code", "integer")
            t.addColumn("other", "string")
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("report_id", "integer")
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }
    }

    void down() {
        dropTable("outcomes")
    }
}
