class MigrateWorkflowConfigs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		
		if (databaseMatches('postgresql')){

			execute("insert into study_site_wf_cfgs (study_organization_id, workflow_cfg_id, \"name\") " + 
                    "select so.id ,(select id from workflow_configuration where workflow_definition_name = 'reportingperiod_coordinating_center'), " +
                    "text('reportingPeriod')  from study_organizations so where so.type = 'SST' and so.id not in (select study_organization_id from study_site_wf_cfgs) " +
                    "UNION " + 
                    "select so.id ,(select id from workflow_configuration where workflow_definition_name = 'expedited_domestic'), " +
                    "text('report') from study_organizations so where so.type = 'SST' and so.id not in (select study_organization_id from study_site_wf_cfgs)");
		
		}else if (databaseMatches('oracle')){
//			execute("create table temp_n3 (n1 varchar2(2000))");
//			execute("insert into temp_n3 values ('reportingPeriod')");
//			execute("insert into temp_n3 values ('report')");
//			execute("insert into study_site_wf_cfgs (id, study_organization_id, workflow_cfg_id, name) " + 
//                    "select (select seq_study_site_wf_cfgs_id.nextval from dual),so.id ,(select id from workflow_configuration where workflow_definition_name = 'reportingperiod_coordinating_center'), " +
//                    "(select n1 from temp_n3 where n1 ='reportingPeriod')  from study_organizations so where so.type = 'SST' and so.id not in (select study_organization_id from study_site_wf_cfgs) " +
//                    "UNION " + 
//                    "select (select seq_study_site_wf_cfgs_id.nextval from dual),so.id ,(select id from workflow_configuration where workflow_definition_name = 'expedited_domestic'), " +
//                    "(select n1 from temp_n3 where n1 = 'report') from study_organizations so where so.type = 'SST' and so.id not in (select study_organization_id from study_site_wf_cfgs)");
//			execute("drop table temp_n3")
	    }
		  
	}
	void down(){
		//not required
	}
}