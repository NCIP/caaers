class AssociateWorkflowToReport extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		dropColumn("ae_reports", "workflow_id");
		dropColumn("ae_reports", "review_status_code");
		addColumn("report_schedules","workflow_id", "integer")
		addColumn("report_schedules","review_status_code", "integer")
	}
	
	void down() {
		dropColumn("report_schedules", "workflow_id");
		dropColumn("report_schedules", "review_status_code");
	}
}