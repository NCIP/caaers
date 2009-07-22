class MigrateReportDefinitionGroup extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		if (databaseMatches('postgresql')){
	         execute("update REPORT_CALENDAR_TEMPLATES set report_type_id = (select id from config_properties where code='RT_AdEERS') where expedited = true")
	         
	     }else if (databaseMatches('oracle')){
	         execute("update REPORT_CALENDAR_TEMPLATES set report_type_id = (select id from config_properties where code='RT_AdEERS') where expedited = 1")
	     }
		//update all other records to local
		 execute("update REPORT_CALENDAR_TEMPLATES set report_type_id = (select id from config_properties where code='RT_INST') where report_type_id is null")
	}
	void down(){
		 execute("update REPORT_CALENDAR_TEMPLATES set report_type_id = null")
	}
}