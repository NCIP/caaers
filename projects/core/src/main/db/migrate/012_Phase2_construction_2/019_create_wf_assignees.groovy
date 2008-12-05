class CreateWFRecipient extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("wf_assignees") { t ->
        	t.addColumn("grid_id", "string")
            t.addVersionColumn()
            t.addColumn("name", "string")
            t.addColumn("research_staffs_id", "integer")
            t.addColumn("user_role_id","integer")
            t.addColumn("task_config_id", "integer", nullable: false)
            t.addColumn("dtype", "string" , nullable:false)
        }
         execute('ALTER TABLE wf_assignees ADD CONSTRAINT FK_wfassignees_research_staffs FOREIGN KEY (research_staffs_id) REFERENCES research_staffs (id)');
         execute('alter table wf_assignees add constraint fk_wfassignees_taskconfig_id foreign key (task_config_id) references task_configuration (id)')
    }

    void down() {
        dropTable("wf_assignees")
    }
}