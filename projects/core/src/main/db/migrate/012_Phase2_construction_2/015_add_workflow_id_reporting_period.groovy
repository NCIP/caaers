class AddWorkflowIdReportingPeriod extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("ae_reporting_periods","workflow_id", "integer")
		addColumn("ae_reporting_periods","review_status_code", "integer")
	}
	
	void down() {
		dropColumn("ae_reporting_periods", "workflow_id");
		dropColumn("ae_reporting_periods", "review_status_code");
	}
}