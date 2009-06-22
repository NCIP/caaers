class CreateAnatomicSite extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('anatomic_sites') { t ->
            t.addColumn('name', 'string', nullable:false)
            t.addColumn('category', 'string', nullable:false)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }        
    }

    void down() {
        dropTable('anatomic_sites')
    }

}