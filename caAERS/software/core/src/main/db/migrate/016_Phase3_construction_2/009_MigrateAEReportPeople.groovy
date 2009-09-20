class MigrateAeReportPeople extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		  if (databaseMatches('oracle')) {
        	execute('alter table ae_report_people modify code DEFAULT 0')
		  }else{
        	execute('ALTER TABLE ae_report_people ALTER COLUMN code SET DEFAULT 0')
		  }
	}
	void down(){
		//not required
	}
}