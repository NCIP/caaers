class ModifyConcomitantMedications extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
	      	addColumn("concomitant_medications","start_date_day", "integer")
      		addColumn("concomitant_medications","start_date_month", "integer")
      		addColumn("concomitant_medications","start_date_year", "integer")
      		addColumn("concomitant_medications","start_date_zone", "integer", nullable: false, defaultValue: 0)
      		
	      	addColumn("concomitant_medications","end_date_day", "integer")
      		addColumn("concomitant_medications","end_date_month", "integer")
      		addColumn("concomitant_medications","end_date_year", "integer")
      		addColumn("concomitant_medications","end_date_zone", "integer", nullable: false, defaultValue: 0)
      		      		
			addColumn("concomitant_medications", "still_taking_medications", "boolean");
	}
	
	void down() {
		
		dropColumn("concomitant_medications", "start_date_day");
		dropColumn("concomitant_medications", "start_date_month");
		dropColumn("concomitant_medications", "start_date_year");
		dropColumn("concomitant_medications", "start_date_zone");
		
		dropColumn("concomitant_medications", "end_date_day");
		dropColumn("concomitant_medications", "end_date_month");
		dropColumn("concomitant_medications", "end_date_year");
		dropColumn("concomitant_medications", "end_date_zone");
		dropColumn("concomitant_medications", "still_taking_medications");
	}
}