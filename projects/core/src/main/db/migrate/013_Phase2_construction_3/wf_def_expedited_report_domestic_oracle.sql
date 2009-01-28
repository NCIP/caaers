INSERT INTO workflow_configuration(id, workflow_definition_name, default_assignee, enabled, name) VALUES (SEQ_WORKFLOW_CONFIGURATION_ID.nextval,'expedited_domestic','SYSTEM_ADMIN', 1, 'Report Domestic Workflow');
	
INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) 	VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'DRAFT_INCOMPLETE', 1,1,'','Start Expedited Domestic Flow');
	
INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) 	VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'DRAFT_INCOMPLETE', 1,1,'The task "Submit Report To Physician" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Submit Report To Physician');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype)    VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Reporter',7,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Submit For Physician Review',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Reporter',7,seq_wf_transition_configs_id.currval,'r');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) 	VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'PHYSICIAN_REVIEW', 1,1,'The task "Physician Review" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Physician Review');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype)    VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Physician',8,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Approve Report',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Physician',8,seq_wf_transition_configs_id.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Request Additional Information',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Physician',8,seq_wf_transition_configs_id.currval,'r');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'PHYSICIAN_ADDITIONAL_INFO', 1,1,'The task "Provide Additional Information To Physician" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Provide Additional Information To Physician');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype) VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Reporter',7,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Submit For Physician Review',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Reporter',7,seq_wf_transition_configs_id.currval,'r');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'PHYSICIAN_APPROVED', 1,1,'The task "Submit Report To Central Office" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Submit Report To Central Office');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype) VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Site CRA',4,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Submit to Central Office SAE Coordinator',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Site CRA',4,seq_wf_transition_configs_id.currval,'r');
  
INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) 	VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'CENTRAL_OFFICE_REVIEW', 1,2,'The task "Central Office SAE Coordinator Review" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Central Office SAE Coordinator Review');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype) VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Central Office SAE Coordinator',6,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Approve Report',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Central Office SAE Coordinator',6,seq_wf_transition_configs_id.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Request Additional Information',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Central Office SAE Coordinator',6,seq_wf_transition_configs_id.currval,'r');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'CENTRAL_OFFICE_ADDITIONAL_INFO', 1,1,'The task "Provide Additional Information To Central Office" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Provide Additional Information To Central Office');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype) VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Site CRA',4,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Submit to Central Office SAE Coordinator',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Site CRA',4,seq_wf_transition_configs_id.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Submit for Physician Review',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Site CRA',4,seq_wf_transition_configs_id.currval,'r');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) 	VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'SUBMIT_TO_SPONSOR', 1,2,'The task "Submit Report To Sponsor" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Submit Report To Sponsor');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype) VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Central Office SAE Coordinator',6,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Submit Report to Sponsor',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,name, user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Central Office SAE Coordinator',6,seq_wf_transition_configs_id.currval,'r');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'SUBMITTED_TO_SPONSOR', 1,1,'The task "End Expedited Domestic Flow" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','End Expedited Domestic Flow');
