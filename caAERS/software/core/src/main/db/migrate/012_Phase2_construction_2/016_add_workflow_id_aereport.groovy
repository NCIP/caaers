class AddWorkflowIdAeReport extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("ae_reports","workflow_id", "integer")
		addColumn("ae_reports","review_status_code", "integer")
	}
	
	void down() {
		dropColumn("ae_reports", "workflow_id");
		dropColumn("ae_reports", "review_status_code");
	}
}