class AddColsSPAConcomitantMedications extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("spa_concomitant_medications", "start_date", "date");
		addColumn("spa_concomitant_medications", "end_date", "date");
		addColumn("spa_concomitant_medications", "still_taking_medications", "boolean");
	}
	
	void down() {
		dropColumn("spa_concomitant_medications", "start_date");
		dropColumn("spa_concomitant_medications", "end_date");
		dropColumn("spa_concomitant_medications", "still_taking_medications");
	}
}