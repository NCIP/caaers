class ModifyWorkflowAssignee extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 	dropColumn("wf_assignees", "research_staffs_id")
	 	addColumn("wf_assignees","user_id", "integer")
	}
	void down(){
		addColumn("wf_assignees","research_staffs_id", "integer")
		dropColumn("wf_assignees", "user_id")
	}
}