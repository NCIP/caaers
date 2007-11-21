class ModifyStudyParticipantAssignment extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("participant_assignments","load_status", 'integer');
            execute("update participant_assignments set load_status = 1");

    }

    void down() {
    	dropColumn("participant_assignments","load_status")
    }
    
}