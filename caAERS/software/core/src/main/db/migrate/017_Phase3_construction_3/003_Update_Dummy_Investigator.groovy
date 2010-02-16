class UpdateDummyInvestigator extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		execute("update investigators set last_name = 'Investigator' where id = -1111")
	}
	void down() {
		//nothing here
	}
}