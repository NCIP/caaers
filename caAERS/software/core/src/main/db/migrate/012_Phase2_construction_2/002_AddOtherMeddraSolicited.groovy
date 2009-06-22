class AddColsSPAConcomitantMedications extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("solicited_events","other_term_id", "integer")
		execute('ALTER TABLE solicited_events ADD CONSTRAINT FK_other_meddra_solicited FOREIGN KEY (other_term_id) REFERENCES meddra_llt (id)');
	}
	
	void down() {
		dropColumn("solicited_events", "other_term_id");
	}
}