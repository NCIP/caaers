class CreateSPAConcomitantMedications extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		createTable("spa_concomitant_medications") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("assignment_id", "integer", nullable: false)
            t.addColumn("agent_name", "string")
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }
        execute("ALTER TABLE spa_concomitant_medications ADD CONSTRAINT fk_conmed_assignment FOREIGN KEY (assignment_id) REFERENCES participant_assignments")
	}
	
	void down(){
		dropTable("spa_concomitant_medications")
	}
}