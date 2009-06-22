class AddRequiresReportingColToAe extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("adverse_events", "requires_reporting", "boolean");
	}
	
	void down() {
		dropColumn("adverse_events", "requires_reporting");
	}
}