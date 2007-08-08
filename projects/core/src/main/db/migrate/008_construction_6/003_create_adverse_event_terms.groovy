class CreateAdverseEventTerms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	  createTable('ae_terms') { t ->
        	
        	t.addColumn('adverse_event_id', 'integer', nullable:false)  
        	t.addColumn('term_id', 'integer', nullable:true)  
        	t.addColumn('term_type', 'string', nullable:true)  
        	t.addColumn('meddra_code', 'string', nullable:true) 
        	 t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true); 
        }
        
         // Migrating Data
        if (databaseMatches('postgres')) {
	 		 execute("INSERT INTO ae_terms SELECT nextval('ae_terms_id_seq'), id, ctc_term_id,'ctep',NULL,0,id FROM adverse_events")
	 	}
	 	
	 	if (databaseMatches('oracle')) {
            execute("INSERT INTO ae_terms SELECT seq_ae_terms_id.NEXTVAL, id, ctc_term_id,'ctep',NULL,0,id FROM adverse_events")
        }
        
         execute("ALTER TABLE adverse_events DROP CONSTRAINT fk_adv_ev_ctc_term");
         dropColumn("adverse_events", "ctc_term_id")
        
         
    }

    void down() {
    	addColumn("adverse_events", "ctc_term_id","integer")
    	dropTable('ae_terms')
    }
}