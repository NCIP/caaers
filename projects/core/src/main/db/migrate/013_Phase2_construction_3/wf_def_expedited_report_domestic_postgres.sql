INSERT INTO workflow_configuration(workflow_definition_name, default_assignee, enabled, name) VALUES ('expedited_domestic','SYSTEM_ADMIN', true, 'Report Domestic Workflow');
	
INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'DRAFT_INCOMPLETE', true,1,'','Start Expedited Domestic Flow');
	
INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'DRAFT_INCOMPLETE', true,1,'The task "Submit Report To Physician" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Submit Report To Physician');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype)    VALUES ('Reporter',7,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Submit For Physician Review',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Reporter',7,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'PHYSICIAN_REVIEW', true,1,'The task "Physician Review" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Physician Review');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype)    VALUES ('Physician',8,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Approve Report',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Physician',8,currval('wf_transition_configs_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Request Additional Information',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Physician',8,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (currval('workflow_configuration_id_seq'),'PHYSICIAN_ADDITIONAL_INFO', true,1,'The task "Provide Additional Information To Physician" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Provide Additional Information To Physician');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype) VALUES ('Reporter',7,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Submit For Physician Review',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Reporter',7,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (currval('workflow_configuration_id_seq'),'PHYSICIAN_APPROVED', true,1,'The task "Submit Report To Central Office" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Submit Report To Central Office');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype) VALUES ('Site CRA',4,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Submit to Central Office SAE Coordinator',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Site CRA',4,currval('wf_transition_configs_id_seq'),'r');
  
INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'CENTRAL_OFFICE_REVIEW', true,2,'The task "Central Office SAE Coordinator Review" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Central Office SAE Coordinator Review');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype) VALUES ('Central Office SAE Coordinator',6,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Approve Report',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Central Office SAE Coordinator',6,currval('wf_transition_configs_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Request Additional Information',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Central Office SAE Coordinator',6,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (currval('workflow_configuration_id_seq'),'CENTRAL_OFFICE_ADDITIONAL_INFO', true,1,'The task "Provide Additional Information To Central Office" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Provide Additional Information To Central Office');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype) VALUES ('Site CRA',4,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Submit to Central Office SAE Coordinator',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Site CRA',4,currval('wf_transition_configs_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Submit for Physician Review',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Site CRA',4,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'SUBMIT_TO_SPONSOR', true,2,'The task "Submit Report To Sponsor" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','Submit Report To Sponsor');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype) VALUES ('Central Office SAE Coordinator',6,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Submit Report to Sponsor',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Central Office SAE Coordinator',6,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (currval('workflow_configuration_id_seq'),'SUBMITTED_TO_SPONSOR', true,1,'The task "End Expedited Domestic Flow" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the evaluation period.','End Expedited Domestic Flow');
