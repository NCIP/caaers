class AddSendToPhysicianForReviewTransitition extends edu.northwestern.bioinformatics.bering.Migration {
	void up() { 
		// for SAE-Coordinator
		execute("UPDATE wf_assignees SET user_role_id = 9 where id = 9");
		execute("UPDATE wf_assignees SET user_role_id = 9 where id = 11");
		execute("UPDATE wf_transition_owners SET user_role_id = 9 where id = 12");
		execute("UPDATE wf_transition_owners SET user_role_id = 9 where id = 13");
		execute("UPDATE wf_transition_owners SET user_role_id = 9 where id = 16");
		
		// for Data-Coordinator
		execute("UPDATE wf_assignees SET user_role_id = 10 where id = 2");
		execute("UPDATE wf_transition_owners SET user_role_id = 10 where id = 2");
		execute("UPDATE wf_transition_owners SET user_role_id = 10 where id = 3");
		
	}
	
	void down(){
	}
}