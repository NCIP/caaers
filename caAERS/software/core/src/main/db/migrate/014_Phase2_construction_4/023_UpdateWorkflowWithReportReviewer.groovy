class UpdateWorkflowWithReportReviewer extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		execute("Update task_configuration set task_name = 'Central Office Report Review' where status_name = 'CENTRAL_OFFICE_REVIEW'");
		execute("Update wf_transition_configs set transition_name = 'Submit to Central Office Reviewer' where transition_name = 'Submit to Central Office SAE Coordinator'");
		execute("Update wf_transition_owners set name = 'Central Office Report Reviewer' where name = 'Central Office SAE Coordinator'");
		external('wf_update_task_configuration.sql');	
	}
	
	void down() {
		//not required
	}
}  