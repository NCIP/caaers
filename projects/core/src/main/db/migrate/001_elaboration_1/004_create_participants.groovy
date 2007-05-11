class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('participants') { t ->
            t.addColumn('instituitional_patient_number', 'string' , nullable:true)
            t.addColumn('institution', 'string' , nullable:true)
            t.addColumn('study_participant_name','string' , nullable:true)
            t.addColumn('first_name', 'string', nullable:true)
	   		t.addColumn('last_name', 'string', nullable:true)
	    	t.addColumn('birth_date', 'date', nullable:true)
            t.addColumn('gender', 'string', nullable:true)
	    	t.addColumn('ethnicity', 'string', nullable:true)
            t.addColumn('race', 'string', nullable:true)
            t.addVersionColumn()
        }
    }

    void down() {
        dropTable('participants')
    }
}