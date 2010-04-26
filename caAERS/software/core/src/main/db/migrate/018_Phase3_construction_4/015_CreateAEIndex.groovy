class CreateAEIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("adverseevent_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("adverseevent_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table adverseevent_index add constraint fk_ae_index_ae_id foreign key (adverseevent_id) references adverse_events (id)')
    }

    void down() {
        dropTable("adverseevent_index")
    }
}