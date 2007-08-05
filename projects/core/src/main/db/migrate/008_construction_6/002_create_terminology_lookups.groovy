class CreateTerminologyLookups extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       
        createTable("terminologies") { t->
            t.addColumn("term_code", "integer",nullable: false )
            t.addColumn("ctc_id", "integer")
            t.addColumn("study_id", "integer",nullable: false)
            t.addVersionColumn();
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        
        // USE this for data migration 
        if (databaseMatches('postgres')) {
	 		 execute("INSERT INTO terminologies SELECT nextval('terminologies_id_seq'), 1, ctc_id,id,0,id FROM studies")
	 	}
	 	
	 	 if (databaseMatches('oracle')) {
	 		 execute("INSERT INTO terminologies SELECT terminologies_id_seq.NEXTVAL, 1, ctc_id,id,0,id FROM studies")
	 	}
	 	dropColumn("studies", "ctc_id")

    }

    void down() {
    	addColumn("studies", "ctc_id","integer")
        dropTable("terminologies", primaryKey: false)
    }
}