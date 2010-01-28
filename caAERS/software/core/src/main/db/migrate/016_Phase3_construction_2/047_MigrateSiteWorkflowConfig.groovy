class MigrateWorkflowConfigs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){

		if (databaseMatches('oracle')){
          
            execute("delete from study_site_wf_cfgs")

			execute("create table temp_n3 (n1 varchar2(2000))");
			execute("insert into temp_n3 values ('reportingPeriod')");
			execute("insert into temp_n3 values ('report')");

            execute("insert into study_site_wf_cfgs (id, name, study_organization_id, workflow_cfg_id)" +
                    "select seq_study_site_wf_cfgs_id.nextval, (select n1 from temp_n3 where n1 ='reportingPeriod') as C4, so.id, (select id from workflow_configuration where workflow_definition_name = 'reportingperiod_coordinating_center') as C3 " +
                    "from study_organizations so where so.type = 'SST' ");
            execute("insert into study_site_wf_cfgs (id, name, study_organization_id, workflow_cfg_id)" +
                    "select seq_study_site_wf_cfgs_id.nextval, (select n1 from temp_n3 where n1 = 'report') as C4, so.id, (select id from workflow_configuration where workflow_definition_name = 'expedited_domestic') as C3 " +
                    "from study_organizations so where so.type = 'SST' ");

			execute("drop table temp_n3")
	    }

	}
	void down(){
	}
}