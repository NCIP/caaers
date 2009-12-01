class MigrateWorkflowConfigs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		
		if (databaseMatches('postgresql')){

			execute("insert into study_site_wf_cfgs (study_organization_id, workflow_cfg_id, \"name\") " + 
                    "select so.id ,(select id from workflow_configuration where workflow_definition_name = 'reportingperiod_coordinating_center'), " +
                    "text('reportingPeriod')  from study_organizations so where so.type = 'SST' and so.id not in (select study_organization_id from study_site_wf_cfgs) " +
                    "UNION " + 
                    "select so.id ,(select id from workflow_configuration where workflow_definition_name = 'expedited_domestic'), " +
                    "text('report') from study_organizations so where so.type = 'SST' and so.id not in (select study_organization_id from study_site_wf_cfgs)");
		
		}
	}
	void down(){
	}
}