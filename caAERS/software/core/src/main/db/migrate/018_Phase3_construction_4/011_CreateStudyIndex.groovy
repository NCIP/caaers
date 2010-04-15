class CreateStudyIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("STUDY_INDEX") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("study_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table STUDY_INDEX add constraint fk_study_index_st_id foreign key (study_id) references studies (id)')
    }

    void down() {
        dropTable("STUDY_INDEX")
    }
}