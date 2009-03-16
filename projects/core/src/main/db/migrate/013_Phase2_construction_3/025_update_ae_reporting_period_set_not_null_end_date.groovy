class updateAETreatmentAssignment extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		if (databaseMatches('oracle')) {
			execute("ALTER TABLE ae_reporting_periods MODIFY end_date NOT NULL");");
		}

		if (databaseMatches('postgres')) {
			execute("ALTER TABLE ae_reporting_periods ALTER COLUMN end_date SET NOT NULL");
	 	}
    }

    void down(){
		if (databaseMatches('oracle')) {
			execute("ALTER TABLE ae_reporting_periods MODIFY end_date NULL");");
		}

		if (databaseMatches('postgres')) {
			execute("ALTER TABLE ae_reporting_periods ALTER COLUMN end_date SET NULL");
	 	}
    }
}
