class UpdateParticipantsSplitDOB extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("participants", "birth_year", "integer")
		addColumn("participants", "birth_month", "integer")
		addColumn("participants", "birth_day", "integer")
		
		if (databaseMatches('postgres')) {
			execute('UPDATE participants SET birth_year=extract(year from birth_date), birth_month=extract(month from birth_date), birth_day=extract(day from birth_date)')
	 	} 
		
	}

	void down() {
		dropColumn("participants", "birth_year")
		dropColumn("participants", "birth_month")
		dropColumn("participants", "birth_day")
	}
}