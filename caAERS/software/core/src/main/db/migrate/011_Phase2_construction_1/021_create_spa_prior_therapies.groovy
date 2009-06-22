class CreateAePriorTherapies extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		createTable("spa_prior_therapies") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("assignment_id", "integer", nullable: false)
            t.addColumn("prior_therapy_id", "integer")
            t.addColumn("other", "string")
            t.addColumn('start_date', 'date', nullable:true)
            t.addColumn('end_date', 'date', nullable:true)
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
            t.addColumn("start_date_day", "integer")
            t.addColumn("start_date_month", "integer")
            t.addColumn("start_date_year", "integer")
            t.addColumn("start_date_zone", "integer", nullable: false, defaultValue: 0)
            t.addColumn("end_date_day", "integer")
            t.addColumn("end_date_month", "integer")
            t.addColumn("end_date_year", "integer")
            t.addColumn("end_date_zone", "integer", nullable: false, defaultValue: 0)
        }
        execute("ALTER TABLE spa_prior_therapies ADD CONSTRAINT fk_spapt_assignment FOREIGN KEY (assignment_id) REFERENCES participant_assignments")
        execute("ALTER TABLE spa_prior_therapies ADD CONSTRAINT fk_spapt_prior_therapy FOREIGN KEY (prior_therapy_id) REFERENCES prior_therapies")
	}
	
	void down(){
	
	}
}