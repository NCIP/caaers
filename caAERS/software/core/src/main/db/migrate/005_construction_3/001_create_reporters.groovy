class CreateReporters extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('reporters') { t ->
            t.addColumn('first_name', 'string', nullable:false)
            t.addColumn('last_name', 'string', nullable:false)
            t.addColumn('middle_name', 'string', nullable:true)
            t.addColumn('maiden_name', 'string', nullable:true)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }        
    }

    void down() {
        dropTable('reporters')
    }

}