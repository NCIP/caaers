class CreateInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('investigators') { t ->           
            t.addColumn('first_name', 'string', nullable:true)
            t.addColumn('last_name', 'string', nullable:true)
            t.addColumn('middle_name', 'string', nullable:true)
            t.addColumn('maiden_name', 'string', nullable:true)
            t.addColumn('birth_date', 'date', nullable:true)
            t.addColumn('gender', 'string', nullable:true)
            t.addColumn('ethnicity', 'string', nullable:true)
            t.addColumn('race', 'string', nullable:true)
            t.addColumn('nci_identifier', 'string', nullable:true)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }
    }

    void down() {
        dropTable('investigators')
    }
}
