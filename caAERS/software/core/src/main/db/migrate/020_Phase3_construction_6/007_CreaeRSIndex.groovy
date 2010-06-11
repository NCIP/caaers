class CreateResearchIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("researchstaff_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("researchstaff_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table researchstaff_index add constraint fk_rs_index_st_id foreign key (researchstaff_id) references research_staffs (id)')
    }

    void down() {
        dropTable("researchstaff_index")
    }
}