class AddSendToPhysicianForReviewTransitition extends edu.northwestern.bioinformatics.bering.Migration {
void up() {  

     if (databaseMatches('postgresql')){
        execute("INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Send to Physician for Review',12)")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Reporter',7,currval('wf_transition_configs_id_seq'),'r')")

     }else if(databaseMatches('oracle')){
     	execute("INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Send to Physician for Review',12)")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Reporter',7,seq_wf_transition_configs_id.currval,'r')")
     }

	}      
  	void down() {        
  	} 
 }