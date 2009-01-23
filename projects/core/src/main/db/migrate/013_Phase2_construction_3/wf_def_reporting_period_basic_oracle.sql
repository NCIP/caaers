INSERT INTO workflow_configuration(id, workflow_definition_name, default_assignee, enabled, name) VALUES (SEQ_WORKFLOW_CONFIGURATION_ID.nextval,'reportingperiod_coordinating_center','SYSTEM_ADMIN', 1, 'Evaluation Period Workflow');
	
INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'DRAFT_INCOMPLETE', 1,1,'','Start Reporting Period Flow');
	
INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'DRAFT_INCOMPLETE', 1,1,'The task "Submit Reporting Period for Data Coordinator Review" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Submit Reporting Period for Data Coordinator Review');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype) VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Site CRA',4,SEQ_TASK_CONFIGURATION_ID.currval,'r');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) 	VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'DATA_COORDINATOR_REVIEW', 1,2,'The task "Data Coordinator Review" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Data Coordinator Review');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype)    VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Coordinating Center Data Coordinator',6,SEQ_TASK_CONFIGURATION_ID.currval,'r');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'DATA_COORDINATOR_ADDITIONAL_INFO', 1,1,'The task "Provide Additional Information To Data Coordinator" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Provide Additional Information To Data Coordinator');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype) VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Site CRA',4,SEQ_TASK_CONFIGURATION_ID.currval,'r');
    
INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) 	VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'READY_FOR_FINALIZE', 1,2,'The task "Finalize Reporting Period" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Finalize Reporting Period');
INSERT INTO wf_assignees(id, name, user_role_id,task_config_id, dtype) VALUES (SEQ_WF_ASSIGNEES_ID.nextval,'Coordinating Center Data Coordinator',6,SEQ_TASK_CONFIGURATION_ID.currval,'r');
  
INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'FINALIZED', 1,1,'The task "Finalized" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','Finalized');

INSERT INTO task_configuration(id, workflow_config_id,status_name, applicable, location, message, task_name) VALUES (SEQ_TASK_CONFIGURATION_ID.nextval,SEQ_WORKFLOW_CONFIGURATION_ID.currval,'FINALIZED', 1,1,'The task "End State" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.','End State');
