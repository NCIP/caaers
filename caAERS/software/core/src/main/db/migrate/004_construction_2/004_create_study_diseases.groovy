class CreateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('study_diseases') { t ->
            t.addColumn('study_id', 'integer', nullable:false)
            t.addColumn('disease_term_id', 'integer', nullable:false)
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        
        if (databaseMatches('oracle')) {
            execute('ALTER TABLE study_diseases ADD lead_disease integer ')
        	} else {
            execute('ALTER TABLE study_diseases ADD lead_disease BOOLEAN ')
        	}   
    }

    void down() {
        dropTable('study_diseases')
    }

}