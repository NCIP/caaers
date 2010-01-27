class MigrateWorkflowConfigs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){

		if (databaseMatches('oracle')){
			execute("create table temp_n3 (n1 varchar2(2000))");
			execute("insert into temp_n3 values ('reportingPeriod')");
			execute("insert into temp_n3 values ('report')");

            execute("alter table study_site_wf_cfgs add (ABC number)");
            execute("insert into study_site_wf_cfgs (id, name, study_organization_id, workflow_cfg_id, ABC)" +
                    "select seq_study_site_wf_cfgs_id.nextval, (select n1 from temp_n3 where n1 ='reportingPeriod') as C4, so.id, (select id from workflow_configuration where workflow_definition_name = 'reportingperiod_coordinating_center') as C3, 1 " +
                    "from study_organizations so where so.type = 'SST' and so.id not in (select study_organization_id from study_site_wf_cfgs where ABC = 1)");
            execute("insert into study_site_wf_cfgs (id, name, study_organization_id, workflow_cfg_id, ABC)" +
                    "select seq_study_site_wf_cfgs_id.nextval, (select n1 from temp_n3 where n1 = 'report') as C4, so.id, (select id from workflow_configuration where workflow_definition_name = 'expedited_domestic') as C3, 2 " +
                    "from study_organizations so where so.type = 'SST' and so.id not in (select study_organization_id from study_site_wf_cfgs where ABC = 2)");
            execute("alter table study_site_wf_cfgs drop column ABC");

			execute("drop table temp_n3")
	    }

	}
	void down(){
	}
}