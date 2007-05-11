class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('participants','middle_name' , 'string' , nullable:true);
    	addColumn('participants','maiden_name' , 'string' , nullable:true);
    }

    void down() {
        removeColumn("participants", "study_participant_name");
    }
}