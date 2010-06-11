class CreateInvestigatorIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("investigator_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("investigator_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table investigator_index add constraint fk_inv_index_st_id foreign key (investigator_id) references investigators (id)')
    }

    void down() {
        dropTable("investigator_index")
    }
}