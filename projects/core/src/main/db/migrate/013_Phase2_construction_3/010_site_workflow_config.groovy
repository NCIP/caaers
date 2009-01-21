class CreateSiteWorkflowConfig extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	createTable("site_workflow_configs") { t ->
	 		t.setIncludePrimaryKey(false)
            t.addColumn("site_id", "integer")
            t.addColumn("wf_config_id", "integer")
            t.addColumn("wf_entity","string")
        }
	 	execute("ALTER TABLE site_workflow_configs ADD CONSTRAINT fk_wfc_site_wf_config_id FOREIGN KEY (wf_config_id)  REFERENCES workflow_configuration (id)")
	 	execute("ALTER TABLE site_workflow_configs ADD CONSTRAINT fk_wfc_site_site_id FOREIGN KEY (site_id)  REFERENCES study_organizations (id)")
	}
	void down(){
		dropTable("site_workflow_configs")
	}
}