class ModifyStudies extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		if (databaseMatches('oracle')) {
			execute("ALTER TABLE studies MODIFY adeers_reporting NULL");
		}

		if (databaseMatches('postgres')) {
			execute("ALTER TABLE studies ALTER COLUMN adeers_reporting DROP NOT NULL");
	 	}
    }

    void down(){
		if (databaseMatches('oracle')) {
			execute("ALTER TABLE studies MODIFY adeers_reporting NOT NULL");
		}

		if (databaseMatches('postgres')) {
			execute("ALTER TABLE studies ALTER COLUMN adeers_reporting SET NOT NULL");
	 	}
    }
}
