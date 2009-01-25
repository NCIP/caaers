class RmoveWorkflowIDFromReport extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		dropColumn("REPORT_SCHEDULES", "workflow_id");
		dropColumn("REPORT_SCHEDULES", "review_status_code");
		
	}
	
	void down() {
		addColumn("REPORT_SCHEDULES","workflow_id", "integer")
		addColumn("REPORT_SCHEDULES","review_status_code", "integer")
	}
}