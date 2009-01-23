drop table site_workflow_configs;

create table site_workflow_configs as (
 select so.id as "site_id" , 
 (select id from workflow_configuration where workflow_definition_name = 'reportingperiod_coordinating_center') as "wf_config_id", 
 text('reportingPeriod') as "wf_entity" from study_organizations so where so.type = 'SST'

 UNION

 select so.id as "site_id",
 (select id from workflow_configuration where workflow_definition_name = 'expedited_domestic') as "wf_config_id", 
 text('report') as "wf_entity" from study_organizations so where so.type = 'SST'
 
 );
ALTER TABLE site_workflow_configs ADD CONSTRAINT fk_wfc_site_site_id FOREIGN KEY (site_id) references study_organizations (id);
ALTER TABLE site_workflow_configs  ADD CONSTRAINT fk_wfc_site_wf_config_id FOREIGN KEY (wf_config_id) references workflow_configuration (id);
