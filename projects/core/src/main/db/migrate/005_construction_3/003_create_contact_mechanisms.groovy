class CreateContactMechanisms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('contact_mechanisms') { t ->
            t.addColumn('type', 'string', nullable:false)
            t.addColumn('value', 'string', nullable:true)
            t.addColumn('person_id', 'string', nullable:false)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }        
    }

    void down() {
        dropTable('contact_mechanisms')
    }

}