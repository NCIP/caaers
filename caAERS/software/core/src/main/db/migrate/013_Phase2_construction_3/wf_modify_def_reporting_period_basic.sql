update task_configuration set message = 'The task "Approved" is assigned to you. Please use the link ${REPORTING_PERIOD_LINK} to access the evaluation period.',  task_name='Approved' ,status_name='APPROVED'   where task_name='Finalized';
update wf_transition_configs set transition_name = 'Submit to Data Coordinator' where transition_name = 'Modify Reporting Period';

delete from wf_transition_owners where transition_config_id in (select id from wf_transition_configs where task_config_id in (select id from task_configuration where task_name='Finalize Reporting Period'));
delete from wf_transition_configs where task_config_id in ( select id from task_configuration where task_name='Finalize Reporting Period');
delete from wf_assignees where task_config_id in ( select id from task_configuration where task_name='Finalize Reporting Period');
delete from task_configuration where task_name='Finalize Reporting Period';