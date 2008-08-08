class CreateSpaMetastaticDisSite extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	createTable('spa_metastatic_disease_sites') { t ->
            t.addColumn('other_site', 'string', nullable:true)            
            t.addColumn('coded_site_id', 'integer', nullable:true)
            t.addColumn('spa_disease_history_id', 'integer', nullable:true)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }
        execute("ALTER TABLE spa_metastatic_disease_sites ADD CONSTRAINT fk_spa_metastatic_anatomic FOREIGN KEY (coded_site_id) REFERENCES anatomic_sites");
        execute("ALTER TABLE spa_metastatic_disease_sites ADD CONSTRAINT fk_spa_metastatic_dis_history FOREIGN KEY (spa_disease_history_id) REFERENCES spa_disease_histories")
    }
    
    void down(){
    	dropTable('spa_metastatic_disease_sites')
    }
}