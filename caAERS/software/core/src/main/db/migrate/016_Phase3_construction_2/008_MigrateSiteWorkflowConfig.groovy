class MigrateWorkflowConfigs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		
		if (databaseMatches('postgresql')){
			execute("insert into study_site_wf_cfgs (\"name\", study_organization_id, workflow_cfg_id) select wf_entity, site_id, wf_config_id from site_workflow_configs");
	    }else if (databaseMatches('oracle')){
	    	execute("insert into study_site_wf_cfgs (id, name, study_organization_id, workflow_cfg_id) select seq_study_site_wf_cfgs_id.nextval, wf_entity, site_id, wf_config_id from site_workflow_configs");
	    }
		  execute("drop table site_workflow_configs")
	}
	void down(){
		//not required
	}
}