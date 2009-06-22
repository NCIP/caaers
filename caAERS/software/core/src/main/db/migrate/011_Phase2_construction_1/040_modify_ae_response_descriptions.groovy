class UpdateTreatments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	 	addColumn("ae_report_descriptions","primary_treatment",'string');
        addColumn("ae_report_descriptions","treatment_time_hour", "integer")
      	addColumn("ae_report_descriptions","treatment_time_minute", "integer")
      	addColumn("ae_report_descriptions","treatment_time_zone", "integer")
      	execute("update ae_report_descriptions set treatment_time_zone = 0");
    }
      
    void down() {
        dropColumn("ae_report_descriptions","primary_treatment");
        dropColumn("ae_report_descriptions","treatment_time_hour");
        dropColumn("ae_report_descriptions","treatment_time_minute");
        dropColumn("ae_report_descriptions","treatment_time_zone");
    }
}
