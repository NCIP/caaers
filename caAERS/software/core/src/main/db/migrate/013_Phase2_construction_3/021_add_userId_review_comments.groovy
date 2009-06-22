class AddEditableWorkflowComments extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("WF_REVIEW_COMMENTS","user_id", "string")
	}
	
	void down() {
		dropColumn("WF_REVIEW_COMMENTS", "user_id");
	}
}

