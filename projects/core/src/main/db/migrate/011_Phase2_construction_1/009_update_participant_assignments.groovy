class UpdateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('participant_assignments','first_course_date' , 'date' , nullable:true);
    }
    void down() {
    	removeColumn("participant_assignments", "first_course_date");
    }
}    	