update task_configuration set  task_name = 'Provide Additional Information to Data Coordinator' where task_name = 'Provide Additional Information To Data Coordinator';
update task_configuration set  task_name = 'Submit Report to Physician' where task_name = 'Submit Report To Physician';
update task_configuration set  task_name = 'Provide Additional Information to Physician' where task_name = 'Provide Additional Information To Physician';
update task_configuration set  task_name = 'Submit Report to Central Office' where task_name = 'Submit Report To Central Office';
update task_configuration set  task_name = 'Submit Report to Sponsor' where task_name = 'Submit Report To Sponsor';
update task_configuration set  task_name = 'Submit Report to Medical Monitor' where task_name = 'Submit Report To Medical Monitor';

update jbpm_taskinstance set  name_ = 'Provide Additional Information to Data Coordinator' where name_ = 'Provide Additional Information To Data Coordinator';
update jbpm_taskinstance set  name_ = 'Submit Report to Physician' where name_ = 'Submit Report To Physician';
update jbpm_taskinstance set  name_ = 'Provide Additional Information to Physician' where name_ = 'Provide Additional Information To Physician';
update jbpm_taskinstance set  name_ = 'Submit Report to Central Office' where name_ = 'Submit Report To Central Office';
update jbpm_taskinstance set  name_ = 'Submit Report to Sponsor' where name_ = 'Submit Report To Sponsor';
update jbpm_taskinstance set  name_ = 'Submit Report to Medical Monitor' where name_ = 'Submit Report To Medical Monitor';