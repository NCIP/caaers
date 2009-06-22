class ModifyWorkflowConfigurationTwo extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("workflow_configuration", "name", "string")
        addColumn("workflow_configuration", "study_site_id", "integer")
         execute('alter table workflow_configuration add constraint fk_wf_conf_study_org_id foreign key (study_site_id) references study_organizations (id)')
    }

    void down() {
        dropColumn("workflow_configuration", "name")
        dropColumn("workflow_configuration", "study_site_id")
    }
}