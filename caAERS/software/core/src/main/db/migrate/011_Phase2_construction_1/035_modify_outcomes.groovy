class ModifyOutcomes extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("outcomes", "adverse_event_id", "integer");
		execute('ALTER TABLE outcomes ADD CONSTRAINT fk_outcomes_ae_id FOREIGN KEY (adverse_event_id) REFERENCES adverse_events (id)');
	}
	
	void down() {
		dropColumn("outcomes", "adverse_event_id");
	}
}