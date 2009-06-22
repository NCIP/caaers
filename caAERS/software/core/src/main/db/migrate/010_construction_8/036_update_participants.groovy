class UpdateParticipantsDropDOB extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		dropColumn("participants", "birth_date")
		
	}

	void down() {
		addColumn("participants", "birth_date", "date")
	}
}