class UpdateParticipantHistory extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn("participant_histories", "height_code","integer",defaultValue: 0)
    	addColumn("participant_histories", "weight_code","integer",defaultValue: 0)
    }

    void down() {
    	dropColumn("participant_histories", "height_code");
    	dropColumn("participant_histories", "weight_code");
    }
}