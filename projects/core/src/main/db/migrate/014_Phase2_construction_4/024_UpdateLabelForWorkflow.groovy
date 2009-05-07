class UpdateLabelForWorkflow extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		execute("Update wf_transition_configs set transition_name = 'Submit to Central Office Report Reviewer' where transition_name = 'Submit to Central Office Reviewer'");
		}
	
	void down() {
		//not required
	}
}  