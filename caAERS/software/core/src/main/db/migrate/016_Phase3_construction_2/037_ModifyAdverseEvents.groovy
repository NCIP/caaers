class AddParticipantAtRisk extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events","participant_at_risk", "boolean")
		
    }

    void down() {
        dropColumn("adverse_events", "participant_at_risk")
    }
}