class CreateSpaDiseaseHistory extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	createTable('spa_disease_histories') { t ->
            t.addColumn('coded_primary_disease_site_id', 'integer', nullable:true)
            t.addColumn('study_disease_id', 'integer', nullable:true)
            t.addColumn('other_primary_disease', 'string', nullable:true)            
            t.addColumn('other_primary_disease_site', 'string', nullable:true)
            t.addColumn('diagnosis_date', 'date', nullable:true)            
            t.addColumn('grid_id', 'string', nullable:true)
            t.addColumn('assignment_id', 'integer', nullable:false)
            t.addColumn('diagnosis_day', 'integer', nullable:true)
            t.addColumn('diagnosis_month', 'integer', nullable:true)
            t.addColumn('diagnosis_year', 'integer', nullable:true)
            t.addColumn('diagnosis_zone', 'integer', nullable:false, defaultValue: 0)
            t.addVersionColumn()
        }
        execute("ALTER TABLE spa_disease_histories ADD CONSTRAINT fk_spa_disease_primary FOREIGN KEY (coded_primary_disease_site_id) REFERENCES anatomic_sites");
        execute("ALTER TABLE spa_disease_histories ADD CONSTRAINT fk_spa_disease_assignment FOREIGN KEY (assignment_id) REFERENCES participant_assignments");
        execute("ALTER TABLE spa_disease_histories ADD CONSTRAINT fk_spa_dhistories_sd FOREIGN KEY (study_disease_id) REFERENCES study_diseases");
    }
    
    void down(){
    	dropTable('spa_disease_histories')
    }
}