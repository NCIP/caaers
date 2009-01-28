class CreateWFTransitionOwners extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("wf_transition_owners") { t ->
            t.addVersionColumn()
            t.addColumn("name", "string")
            t.addColumn("user_id", "integer")
            t.addColumn("user_role_id","integer")
            t.addColumn("transition_config_id", "integer", nullable: false)
            t.addColumn("dtype", "string" , nullable:false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table wf_transition_owners add constraint fk_wftowner_transconfig_id foreign key (transition_config_id) references wf_transition_configs (id)')
    }

    void down() {
        dropTable("wf_transition_owners")
    }
}