class AddAECoordinatorToWorkflow extends edu.northwestern.bioinformatics.bering.Migration {
void up() {  

     if (databaseMatches('postgresql')){
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,1,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,4,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,6,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,7,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,10,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,11,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,14,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,15,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,17,'r')")
		execute("INSERT INTO wf_transition_owners(\"name\", user_role_id, transition_config_id,dtype) VALUES ('Adverse Event Coordinator',6,18,'r')")

     }else if(databaseMatches('oracle')){
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Send to Physician for Review' and  t.task_name = 'Provide Additional Information To Central Office') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Send to Physician for Review' and  t.task_name = 'Provide Additional Information To Physician') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Send to Physician for Review' and  t.task_name = 'Submit Report To Central Office') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Send to Physician for Review' and  t.task_name = 'Submit Report To Physician') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Submit to Central Office SAE Coordinator' and  t.task_name = 'Provide Additional Information To Central Office') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Submit to Central Office SAE Coordinator' and  t.task_name = 'Submit Report To Central Office') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Submit to Central Office SAE Coordinator' and  t.task_name = 'Submit Report To Physician') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Submit to Data Coordinator' and  t.task_name = 'Approved') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Submit to Data Coordinator' and  t.task_name = 'Provide Additional Information To Data Coordinator') ,'r')")
		execute("INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Adverse Event Coordinator',6, (select tr.id from wf_transition_configs tr join task_configuration t on tr.task_config_id = t.id join workflow_configuration wfc on wfc.id = t.workflow_config_id and tr.transition_name = 'Submit to Data Coordinator' and  t.task_name = 'Submit Reporting Period for Data Coordinator Review') ,'r')")

     }

	}      
  	void down() {        
  	} 
 }