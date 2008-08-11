class AddColsSPAConcomitantMedications extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
	      	addColumn("spa_concomitant_medications","start_date_day", "integer")
      		addColumn("spa_concomitant_medications","start_date_month", "integer")
      		addColumn("spa_concomitant_medications","start_date_year", "integer")
      		addColumn("spa_concomitant_medications","start_date_zone", "integer", nullable: false, defaultValue: 0)
      		
	      	addColumn("spa_concomitant_medications","end_date_day", "integer")
      		addColumn("spa_concomitant_medications","end_date_month", "integer")
      		addColumn("spa_concomitant_medications","end_date_year", "integer")
      		addColumn("spa_concomitant_medications","end_date_zone", "integer", nullable: false, defaultValue: 0)
      		      		
			addColumn("spa_concomitant_medications", "still_taking_medications", "boolean");
	}
	
	void down() {
		
		dropColumn("spa_concomitant_medications", "start_date_day");
		dropColumn("spa_concomitant_medications", "start_date_month");
		dropColumn("spa_concomitant_medications", "start_date_year");
		dropColumn("spa_concomitant_medications", "start_date_zone");
		dropColumn("spa_concomitant_medications", "end_date_day");
		
		dropColumn("spa_concomitant_medications", "end_date_month");
		dropColumn("spa_concomitant_medications", "end_date_year");
		dropColumn("spa_concomitant_medications", "end_date_zone");
		dropColumn("spa_concomitant_medications", "still_taking_medications");
	}
}