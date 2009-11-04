class AddWorkflowFlagToReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("report_calendar_templates","workflow_enabled", "boolean",defaultValue: 0, nullable: false);
	}
	
	void down() {
		dropColumn("report_calendar_templates", "workflow_enabled");
	}
}