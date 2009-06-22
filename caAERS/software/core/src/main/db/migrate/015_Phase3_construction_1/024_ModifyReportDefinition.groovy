class ModifyReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		dropColumn("REPORT_CALENDAR_TEMPLATES","expedited")
	}
	void down(){
		addColumn("REPORT_CALENDAR_TEMPLATES","expedited", "boolean", defaultValue: 0)
	}
}