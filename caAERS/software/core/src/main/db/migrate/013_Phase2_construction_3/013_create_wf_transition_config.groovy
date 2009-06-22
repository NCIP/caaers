class CreateWFTransitionConfig extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("wf_transition_configs") { t ->
            t.addVersionColumn()
            t.addColumn("transition_name", "string")
            t.addColumn("task_config_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table wf_transition_configs add constraint fk_wftran_taskconfig_id foreign key (task_config_id) references task_configuration (id)')
    }

    void down() {
        dropTable("wf_transition_configs")
    }
}