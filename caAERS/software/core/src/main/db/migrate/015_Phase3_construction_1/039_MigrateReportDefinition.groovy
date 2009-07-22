class MigrateReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		 execute("update REPORT_CALENDAR_TEMPLATES set group_id = report_type_id")
		 execute("update REPORT_CALENDAR_TEMPLATES set report_type = 2")
		 execute("update REPORT_CALENDAR_TEMPLATES set report_type = 1 where time_scale_unit_code= 3 and duration = 24")
	}
	void down(){
		 execute("update REPORT_CALENDAR_TEMPLATES set group_id = null")
		 execute("update REPORT_CALENDAR_TEMPLATES set report_type = null")
	}
}