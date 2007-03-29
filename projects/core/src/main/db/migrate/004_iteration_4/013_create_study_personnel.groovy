class CreateStudyPersonnel extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('study_personnel') { t ->                     
            t.addColumn('research_staffs_id', 'integer', nullable:false)
            t.addColumn('study_sites_id', 'integer', nullable:false)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }
    }

    void down() {
        dropTable('study_personnel')
    }
}