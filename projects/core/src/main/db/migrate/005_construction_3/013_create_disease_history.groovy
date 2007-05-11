class CreateDiseaseHistory extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('disease_history') { t ->
            t.addColumn('anatomic_site_id', 'integer', nullable:true)
            t.addColumn('study_disease_id', 'integer', nullable:true)
            t.addColumn('other_disease_code', 'string', nullable:true)            
            t.addColumn('other_disease_site_code', 'string', nullable:true)
            t.addColumn('diagnosis_date', 'date', nullable:true)            
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }        
    }

    void down() {
        dropTable('disease_history')
    }

}