
INSERT INTO task_configuration(id,workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,currval('workflow_configuration_id_seq'),'SUBMIT_TO_MEDICAL_MONITOR', true,2,
'The task "Submit Report To Medical Monitor" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the report.','Submit Report To Medical Monitor');
INSERT INTO wf_assignees(id,"name", user_role_id,task_config_id, dtype)    VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Central Office SAE Coordinator',9,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Send to Medical Monitor for Review',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,"name", user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Central Office SAE Coordinator',9,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(id,workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,currval('workflow_configuration_id_seq'),'MEDICAL_MONITOR_REVIEW', true,4,
'The task "Medical Monitor Review" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the report.','Medical Monitor Review');
INSERT INTO wf_assignees(id,"name", user_role_id,task_config_id, dtype)    VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Medical Monitor',11,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Approve Report',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,"name", user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Medical Monitor',11,currval('wf_transition_configs_id_seq'),'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Request Additional Information',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,"name", user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Medical Monitor',11,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(id,workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,currval('workflow_configuration_id_seq'),'MEDICAL_MONITOR_ADDITIONAL_INFO', true,2,
'The task "Provide Additional Information To Medical Monitor" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the report.','Provide Additional Information To Medical Monitor');
INSERT INTO wf_assignees(id,"name", user_role_id,task_config_id, dtype)    VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Central Office SAE Coordinator',9,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Submit to Medical Monitor for Review',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,"name", user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Central Office SAE Coordinator',9,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(id,workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,currval('workflow_configuration_id_seq'),'COMPILE_COMMENTS', true,2,
'The task "Compile Comments" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the report.','Compile Comments');
INSERT INTO wf_assignees(id,"name", user_role_id,task_config_id, dtype)    VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Central Office SAE Coordinator',9,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Send feedback, comments and queries to CRA',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,"name", user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Central Office SAE Coordinator',9,currval('wf_transition_configs_id_seq'),'r');

INSERT INTO task_configuration(id,workflow_config_id,status_name, applicable, "location", message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,currval('workflow_configuration_id_seq'),'FINALIZE_REPORT_AND_SUBMIT', true,1,
'The task "Finalize Report and Submit" is assigned to you. Please use the link ${EXPEDITED_REPORT_LINK} to access the report.','Finalize Report and Submit');
INSERT INTO wf_assignees(id,"name", user_role_id,task_config_id, dtype)    VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Reporter',7,SEQ_TASK_CONFIGURATION_ID.currval,'r');
INSERT INTO wf_transition_configs(id,transition_name, task_config_id) VALUES (seq_wf_transition_configs_id.nextval,'Submit report to External System',SEQ_TASK_CONFIGURATION_ID.currval);
INSERT INTO wf_transition_owners(id,"name", user_role_id, transition_config_id,dtype) VALUES (seq_wf_transition_owners_id.nextval,'Reporter',7,currval('wf_transition_configs_id_seq'),'r');