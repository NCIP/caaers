class CreateTaskConfiguration extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("task_configuration") { t ->
        	t.addColumn("grid_id", "string")
            t.addVersionColumn()
            t.addColumn("status_name", "string", nullable: false)
            t.addColumn("applicable", "boolean", nullable: false)
            t.addColumn("location", "integer", nullable:false)
            t.addColumn("workflow_config_id", "integer", nullable:false)
            
        }
        execute('alter table task_configuration add constraint fk_wf_config_id foreign key (workflow_config_id) references workflow_configuration (id)')
    }

    void down() {
        dropTable("task_configuration")
    }
}