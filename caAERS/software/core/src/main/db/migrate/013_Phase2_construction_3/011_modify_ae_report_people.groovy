class ModifyReportPerson extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 	addColumn("ae_report_people", "user_id", "integer")
	}
	void down(){
		dropColumn("ae_report_people","user_id")
	}
}