class AddSeriousColToAe extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("adverse_events", "serious", "boolean");
	}
	
	void down() {
		dropColumn("adverse_events", "serious");
	}
}