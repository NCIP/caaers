class UpdateTreatments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	 	addColumn("treatments","primary_treatment",'string');
        addColumn("treatments","treatment_time_hour", "integer")
      	addColumn("treatments","treatment_time_minute", "integer")
      	addColumn("treatments","treatment_time_zone", "integer")
      	execute("update treatments set treatment_time_zone = 0");
    }
      
    void down() {
        dropColumn("treatments","primary_treatment");
        dropColumn("treatments","treatment_time_hour");
        dropColumn("treatments","treatment_time_minute");
        dropColumn("treatments","treatment_time_zone");
    }
}
