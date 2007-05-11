class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('identifiers') { t ->
            t.addColumn('value', 'string' , nullable:true)
            t.addColumn('type', 'string' , nullable:true)
            t.addColumn('source', 'string' , nullable:true)
            t.addColumn('participant_id', 'integer', nullable:true)
            t.addVersionColumn()  
        }
        if (databaseMatches('oracle')) {
            execute('ALTER TABLE identifiers ADD primary_indicator integer ')
        	} else {
            execute('ALTER TABLE identifiers ADD primary_indicator BOOLEAN ')
        	}   
    }

    void down() {
    	dropTable('identifiers')
    }
}