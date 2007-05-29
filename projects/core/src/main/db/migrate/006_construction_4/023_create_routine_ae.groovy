class AddRoutineAE extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
        createTable("ae_routine_reports") { t ->
            t.addVersionColumn()
            t.addColumn("assignment_id", "integer", nullable: false)
            t.addColumn("start_date", "date", nullable: false)
            t.addColumn("end_date", "date", nullable: false)
            t.addColumn("grid_id","string", nullable: true)
        }
        
        addColumn("adverse_events", "routine_report_id", "integer")
        execute("ALTER TABLE adverse_events DROP CONSTRAINT fk_adv_ev_report");
        addColumn("adverse_events", "routine_list_index", "integer", defaultValue: 0)
    }

    void down() {
    	dropTable("ae_routine_reports") 
    }
}