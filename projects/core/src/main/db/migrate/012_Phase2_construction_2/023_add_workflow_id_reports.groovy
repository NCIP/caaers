class AddWorkflowIdReports extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("REPORT_SCHEDULES","workflow_id", "integer")
		addColumn("REPORT_SCHEDULES","review_status_code", "integer")
	}
	
	void down() {
		dropColumn("REPORT_SCHEDULES", "workflow_id");
		dropColumn("REPORT_SCHEDULES", "review_status_code");
	}
}