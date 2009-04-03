class AddAdverseEventGradedDate extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("adverse_events","graded_date", "date")
	}
	
	void down() {
		dropColumn("adverse_events", "graded_date");
		
	}
}