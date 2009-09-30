class ModifyInvestigationalNewDrugs extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
    	if (databaseMatches('oracle')) {
			execute("ALTER TABLE study_agent_inds MODIFY ind_id NULL");
		}

		if (databaseMatches('postgres')) {
			execute("ALTER TABLE study_agent_inds ALTER COLUMN ind_id DROP NOT NULL");
	 	}
		
    }

    void down() {
        //not possible.
    }
}
