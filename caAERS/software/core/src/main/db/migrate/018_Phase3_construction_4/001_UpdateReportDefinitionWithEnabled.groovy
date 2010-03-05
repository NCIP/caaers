class AddEnabledFlagToReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("report_calendar_templates","enabled", "boolean",defaultValue: 1, nullable: false);
	}
	
	void down() {
		dropColumn("report_calendar_templates", "enabled");
	}
}
