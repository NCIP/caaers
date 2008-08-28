class UpdateTreatments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	 	addColumn("treatments","primary_treatment",'string');
        addColumn("treatments","treatment_time_hour", "integer")
      	addColumn("treatments","treatment_time_minute", "integer")

    }
      
    void down() {
        dropColumn("treatments","primary_treatment");
        dropColumn("treatments","treatment_time_hour");
        dropColumn("treatments","treatment_time_minute");
    }
}
