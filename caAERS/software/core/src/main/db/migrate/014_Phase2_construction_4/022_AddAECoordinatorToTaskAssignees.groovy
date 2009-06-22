class AddAECoordinatorToTaskAssignees extends edu.northwestern.bioinformatics.bering.Migration {
void up() {  

     if (databaseMatches('postgresql')){
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Adverse Event Coordinator',6,2,'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Adverse Event Coordinator',6,4,'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Adverse Event Coordinator',6,9,'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Adverse Event Coordinator',6,11,'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Adverse Event Coordinator',6,12,'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Adverse Event Coordinator',6,14,'r')")

     }else if(databaseMatches('oracle')){
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Adverse Event Coordinator',6,(select t.id from task_configuration t join workflow_configuration wfc on wfc.id = t.workflow_config_id and t.task_name = 'Submit Reporting Period for Data Coordinator Review'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Adverse Event Coordinator',6,(select t.id from task_configuration t join workflow_configuration wfc on wfc.id = t.workflow_config_id and t.task_name = 'Provide Additional Information To Data Coordinator'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Adverse Event Coordinator',6,(select t.id from task_configuration t join workflow_configuration wfc on wfc.id = t.workflow_config_id and t.task_name = 'Submit Report To Physician'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Adverse Event Coordinator',6,(select t.id from task_configuration t join workflow_configuration wfc on wfc.id = t.workflow_config_id and t.task_name = 'Provide Additional Information To Physician'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Adverse Event Coordinator',6,(select t.id from task_configuration t join workflow_configuration wfc on wfc.id = t.workflow_config_id and t.task_name = 'Submit Report To Central Office'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Adverse Event Coordinator',6,(select t.id from task_configuration t join workflow_configuration wfc on wfc.id = t.workflow_config_id and t.task_name = 'Provide Additional Information To Central Office'),'r')")

     }

	}      
  	void down() {        
  	} 
 }