class AddAEReportingPeriod extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
        createTable("ae_reporting_periods") { t ->
            t.addVersionColumn()
            t.addColumn("assignment_id", "integer", nullable:false)
            t.addColumn("start_date", "date", nullable: false)
            t.addColumn("end_date", "date", nullable: false)
            t.addColumn("description", "string", nullable: true)
            t.addColumn("cycle_number", "integer", nullable: true)
            t.addColumn("grid_id", "string", nullable: true)
            t.addColumn("treatment_assignment_id", "integer", nullable: true)
            t.addColumn("epoch_id", "integer", nullable:false)
        }
        execute('ALTER TABLE ae_reporting_periods ADD CONSTRAINT fk_assignment_id FOREIGN KEY (assignment_id) REFERENCES participant_assignments');
        execute('ALTER TABLE ae_reporting_periods ADD CONSTRAINT fk_treatment_id FOREIGN KEY (treatment_assignment_id) REFERENCES treatment_assignment');
        execute('ALTER TABLE ae_reporting_periods ADD CONSTRAINT fk_epoch_id FOREIGN KEY (epoch_id) REFERENCES epochs');
    }

    void down() {
    	dropTable("ae_reporting_periods") 
    }
}