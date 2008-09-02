class ModifyAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		dropColumn("adverse_events", "serious");
	}
	
	void down() {
		addColumn("adverse_events", "serious", "boolean");
	}
}