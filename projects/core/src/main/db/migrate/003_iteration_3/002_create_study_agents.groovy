class CreateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('study_agents') { t ->
            t.addColumn('study_id', 'integer', nullable:false)
            t.addColumn('agent_id', 'integer', nullable:false)
            t.addColumn('investigational_new_drug_identifier', 'string', nullable:true)
            t.addColumn('start_date', 'date', nullable:true)
            t.addColumn('end_date', 'date', nullable:true)
            t.addColumn('grid_id' , 'string' , nullable:true);
            t.addVersionColumn()
        }
        
        if (databaseMatches('oracle')) {
            execute('ALTER TABLE study_agents ADD investigational_new_drug_indicator integer ')
        	} else {
            execute('ALTER TABLE study_agents ADD investigational_new_drug_indicator BOOLEAN ')
        	}   
    }

    void down() {
        dropTable('study_agents')
    }

}