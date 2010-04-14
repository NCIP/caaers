class UpdateReportAssignees extends edu.northwestern.bioinformatics.bering.Migration {
  void up() {
     execute("delete from wf_assignees");
     
     if (databaseMatches('postgresql')){
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Site CRA',4,(select t.id from task_configuration t where t.task_name = 'Submit Reporting Period for Data Coordinator Review'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Site CRA',4,(select t.id from task_configuration t where t.task_name = 'Provide Additional Information To Data Coordinator'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Site CRA',4,(select t.id from task_configuration t where t.task_name = 'Approved'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Coordinating Center Data Coordinator',10,(select t.id from task_configuration t where t.task_name = 'Data Coordinator Review'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Reporter',7,(select t.id from task_configuration t where t.task_name = 'Submit Report To Physician'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Reporter',7,(select t.id from task_configuration t where t.task_name = 'Provide Additional Information To Physician'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Reporter',7,(select t.id from task_configuration t where t.task_name = 'Submit Report To Central Office'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Reporter',7,(select t.id from task_configuration t where t.task_name = 'Provide Additional Information To Central Office'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Physician',8,(select t.id from task_configuration t where t.task_name = 'Physician Review'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Central Office SAE Coordinator',9,(select t.id from task_configuration t where t.task_name = 'Central Office Report Review'),'r')")
		execute("INSERT INTO wf_assignees(\"name\", user_role_id, task_config_id,dtype) VALUES ('Central Office SAE Coordinator',9,(select t.id from task_configuration t where t.task_name = 'Submit Report To Sponsor'),'r')")
		
     }else if(databaseMatches('oracle')){
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Site CRA',4,(select t.id from task_configuration t where t.task_name = 'Submit Reporting Period for Data Coordinator Review'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Site CRA',4,(select t.id from task_configuration t where t.task_name = 'Provide Additional Information To Data Coordinator'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Site CRA',4,(select t.id from task_configuration t where t.task_name = 'Approved'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Coordinating Center Data Coordinator',10,(select t.id from task_configuration t where t.task_name = 'Data Coordinator Review'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Reporter',7,(select t.id from task_configuration t where t.task_name = 'Submit Report To Physician'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Reporter',7,(select t.id from task_configuration t where t.task_name = 'Provide Additional Information To Physician'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Reporter',7,(select t.id from task_configuration t where t.task_name = 'Submit Report To Central Office'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Reporter',7,(select t.id from task_configuration t where t.task_name = 'Provide Additional Information To Central Office'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Physician',8,(select t.id from task_configuration t where t.task_name = 'Physician Review'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Central Office SAE Coordinator',9,(select t.id from task_configuration t where t.task_name = 'Central Office Report Review'),'r')")
		execute("INSERT INTO wf_assignees(id,name, user_role_id, task_config_id,dtype) VALUES (seq_wf_assignees_id.nextval,'Central Office SAE Coordinator',9,(select t.id from task_configuration t where t.task_name = 'Submit Report To Sponsor'),'r')")
     }
     
  }
  void down(){
     //NA
  }
}
