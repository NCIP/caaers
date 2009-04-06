class UpdateWorkflowTaskConfig extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		execute("update wf_transition_configs set transition_name = 'Send to Physician for Review' where id = 10")
	}
	
	void down() {
		//not possible
		
	}
}