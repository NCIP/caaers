class CreateMetastaticDiseaseSite extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('metastatic_disease_site') { t ->
            t.addColumn('other_metastatic_disease_site', 'string', nullable:true)            
            t.addColumn('anatomic_site_id', 'integer', nullable:true)
            t.addColumn('disease_history_id', 'integer', nullable:true)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }        
    }

    void down() {
        dropTable('metastatic_disease_site')
    }

}