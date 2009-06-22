class ModifyWorkflowConfiguration extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 	dropColumn("workflow_configuration", "study_site_id")
	}
	void down(){
		addColumn("workflow_configuration","study_site_id", "integer")
	}
}