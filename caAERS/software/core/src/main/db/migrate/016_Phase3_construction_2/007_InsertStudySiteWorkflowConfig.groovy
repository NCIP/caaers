class StudySiteWorkflowConfig extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("study_site_wf_cfgs") { t ->
            t.addVersionColumn()
            t.addColumn("name", "string")
            t.addColumn("grid_id", "string")
            t.addColumn("study_organization_id", "integer")
            t.addColumn("workflow_cfg_id", "integer")
        }
        execute('alter table study_site_wf_cfgs add constraint fk_ss_wcfg_so_id foreign key (study_organization_id) references study_organizations (id)')
        execute('alter table study_site_wf_cfgs add constraint fk_ss_wfcfg_wfcfg_id foreign key (workflow_cfg_id) references workflow_configuration (id)')
    }

    void down() {
        dropTable("study_site_wf_cfgs")
    }
}