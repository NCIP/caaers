class RemoveWorkflowIdAeReport extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		
		dropColumn("ae_reports", "workflow_id");
		dropColumn("ae_reports", "review_status_code");
		
	}
	
	void down() {
		addColumn("ae_reports","workflow_id", "integer")
		addColumn("ae_reports","review_status_code", "integer")
	}
}