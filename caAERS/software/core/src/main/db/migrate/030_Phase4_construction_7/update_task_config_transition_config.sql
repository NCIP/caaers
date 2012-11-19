update task_configuration set  status_name = 'COORDINATING_CENTER_REVIEW' where status_name = 'CENTRAL_OFFICE_REVIEW';
update task_configuration set  status_name = 'COORDINATING_CENTER_ADDITIONAL_INFO' where status_name = 'CENTRAL_OFFICE_ADDITIONAL_INFO';
update task_configuration set  status_name = 'SEND_TO_MEDICAL_MONITOR' where status_name = 'SUBMIT_TO_MEDICAL_MONITOR';

update task_configuration set  task_name = 'Coordinating Center Review' where task_name = 'Central Office Report Review';
update task_configuration set  task_name = 'Submit Report to Coordinating Center' where task_name = 'Submit Report to Central Office';
update task_configuration set  task_name = 'Provide Additional Information to Coordinating Center' where task_name = 'Provide Additional Information To Central Office';
update task_configuration set  task_name = 'Send Report to Medical Monitor' where task_name = 'Submit Report to Medical Monitor';

update jbpm_taskinstance set  name_ = 'Coordinating Center Review' where name_ = 'Central Office Report Review';
update jbpm_taskinstance set  name_ = 'Submit Report to Coordinating Center' where name_ = 'Submit Report to Central Office';
update jbpm_taskinstance set  name_ = 'Provide Additional Information to Coordinating Center' where name_ = 'Provide Additional Information To Central Office';
update jbpm_taskinstance set  name_ = 'Send Report to Medical Monitor' where name_ = 'Submit Report to Medical Monitor';


update wf_transition_configs set  transition_name = 'Send to Medical Monitor for Review' where transition_name = 'Submit to Medical Monitor for Review';
update wf_transition_configs set  transition_name = 'Submit to Coordinating Center Reviewer' where transition_name = 'Submit to Central Office Report Reviewer';

update jbpm_transition set  name_ = 'Send to Medical Monitor for Review' where name_ = 'Submit to Medical Monitor for Review';
update jbpm_transition set  name_ = 'Submit to Coordinating Center Reviewer' where name_ = 'Submit to Central Office Report Reviewer';
