class UpdateTreatments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	 addColumn("treatments","primary_treatment",'string');
	 addColumn("treatments","approximate_time",'date');
    }
      
    void down() {
        dropColumn("treatments","total_courses");
        dropColumn("treatments","approximate_time");
    }
}
