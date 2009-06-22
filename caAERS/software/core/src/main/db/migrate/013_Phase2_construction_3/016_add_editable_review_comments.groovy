class AddEditableWorkflowComments extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("WF_REVIEW_COMMENTS","editable", "boolean")
	}
	
	void down() {
		dropColumn("WF_REVIEW_COMMENTS", "editable");
	}
}

