class UpdateParticipantHistoryHeightAndWeightUnits extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		 execute("update participant_histories set height = 2.54*height where height_unit like 'Inch'");
		 execute("update participant_histories set height_unit = 'Centimeter' where height_unit like 'Inch'");
		 execute("update participant_histories set weight = 0.453*weight where weight_unit like 'Pound'");
		 execute("update participant_histories set weight_unit = 'Kilogram' where weight_unit like 'Pound'");
    }

    void down() {
    
    }
}