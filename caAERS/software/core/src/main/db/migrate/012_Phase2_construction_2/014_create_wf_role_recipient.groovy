class CreateRoleRecipient extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("wf_role_recipient") { t ->
        	t.addColumn("grid_id", "string")
            t.addVersionColumn()
            t.addColumn("code", "integer")
            t.addColumn("task_config_id", "integer", nullable: false)
        }
        execute('alter table wf_role_recipient add constraint fk_wf_task_role_id foreign key (task_config_id) references task_configuration (id)')
    }

    void down() {
        dropTable("wf_role_recipient")
    }
}