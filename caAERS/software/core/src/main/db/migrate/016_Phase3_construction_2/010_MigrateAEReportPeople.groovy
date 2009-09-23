class MigrateAeReportPeople extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		  execute("update ae_report_people set code = 0");
	}
	void down(){
		//not required
	}
}