INSERT INTO workflow_configuration(workflow_definition_name, default_assignee, enabled, name) VALUES ('reportingperiod_coordinating_center','SYSTEM_ADMIN', true, 'Evaluation Period Workflow');
	
INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'DRAFT_INCOMPLETE', true,1,'','Start Reporting Period Flow');
	
INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'DRAFT_INCOMPLETE', true,1,'The task "Submit Reporting Period for Data Coordinator Review" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Submit Reporting Period for Data Coordinator Review');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype)    VALUES ('Site CRA',4,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Submit to Data Coordinator',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Site CRA',4,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'DATA_COORDINATOR_REVIEW', true,2,'The task "Data Coordinator Review" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Data Coordinator Review');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype)    VALUES ('Coordinating Center Data Coordinator',6,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Request Additional Information',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Coordinating Center Data Coordinator',6,currval('wf_transition_configs_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Approve Reporting Period',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Coordinating Center Data Coordinator',6,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (currval('workflow_configuration_id_seq'),'DATA_COORDINATOR_ADDITIONAL_INFO', true,1,'The task "Provide Additional Information To Data Coordinator" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Provide Additional Information To Data Coordinator');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype) VALUES ('Site CRA',4,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Submit to Data Coordinator',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Site CRA',4,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) 	VALUES (currval('workflow_configuration_id_seq'),'READY_FOR_FINALIZE', true,2,'The task "Finalize Reporting Period" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Finalize Reporting Period');
INSERT INTO wf_assignees("name", user_role_id,task_config_id, dtype) VALUES ('Coordinating Center Data Coordinator',6,currval('task_configuration_id_seq'),'r');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Finalize Reporting Period',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Coordinating Center Data Coordinator',6,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (currval('workflow_configuration_id_seq'),'FINALIZED', true,1,'The task "Finalized" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Finalized');
INSERT INTO wf_transition_configs(transition_name, task_config_id) VALUES ('Modify Reporting Period',currval('task_configuration_id_seq'));
INSERT INTO wf_transition_owners("name", user_role_id, transition_config_id,dtype) VALUES ('Site CRA',4,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (currval('workflow_configuration_id_seq'),'FINALIZED', true,1,'The task "End State" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','End State');
