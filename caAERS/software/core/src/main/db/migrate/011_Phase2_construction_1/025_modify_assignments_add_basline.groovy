class UpdateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('participant_assignments','baseline_performance' , 'string' , nullable:true);
    }
    void down() {
    	removeColumn("participant_assignments", "baseline_performance");
    }
} 