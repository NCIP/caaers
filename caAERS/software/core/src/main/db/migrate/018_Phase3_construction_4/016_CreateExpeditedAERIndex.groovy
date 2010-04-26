class CreateExpAEIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("expedited_ae_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("expedited_ae_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table expedited_ae_index add constraint fk_exaer_index_exae_id foreign key (expedited_ae_id) references ae_reports (id)')
    }

    void down() {
        dropTable("expedited_ae_index")
    }
}