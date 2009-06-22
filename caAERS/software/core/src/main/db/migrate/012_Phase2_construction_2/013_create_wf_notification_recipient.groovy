class CreateNotificationRecipient extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("wf_notification_recipient") { t ->
        	t.addColumn("grid_id", "string")
            t.addVersionColumn()
            t.addColumn("name", "string")
            t.addColumn("task_config_id", "integer", nullable: false)
        }
        execute('alter table wf_notification_recipient add constraint fk_wf_task_config_id foreign key (task_config_id) references task_configuration (id)')
    }

    void down() {
        dropTable("wf_notification_recipient")
    }
}