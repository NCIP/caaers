class ModifyReportCalender extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("report_calendar_templates", "report_format_type", "integer", nullable: false, defaultValue: 2);
	}
	
	void down() {
		dropColumn("report_calendar_templates", "report_format_type");
	}
}