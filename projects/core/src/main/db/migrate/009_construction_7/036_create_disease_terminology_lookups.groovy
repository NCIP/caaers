class CreateDiseaseTerminologyLookups extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       
        createTable("disease_terminologies") { t->
            t.addColumn("term_code", "integer",nullable: false )
            t.addColumn("study_id", "integer",nullable: false)
            t.addVersionColumn();
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        
        // USE this for data migration 
        if (databaseMatches('postgres')) {
	 		 execute("INSERT INTO disease_terminologies SELECT nextval('disease_terminologies_id_seq'), 1,id,0,id FROM studies")
	 	}
	 	
	 	 if (databaseMatches('oracle')) {
	 		 execute("INSERT INTO disease_terminologies SELECT seq_disease_terminologies_id.NEXTVAL, 1,id,0,id FROM studies")
	 	}
	 	
    }

    void down() {
        dropTable('disease_terminologies')
    }
}