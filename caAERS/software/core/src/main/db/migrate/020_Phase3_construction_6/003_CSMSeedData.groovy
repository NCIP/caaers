class CsmSeedData extends edu.northwestern.bioinformatics.bering.Migration {
	
	void up() {
		if (databaseMatches('oracle')) {
            external("CSM_SeedData_Oracle.sql")
        } else if (databaseMatches('postgresql')){
            external("CSM_SeedData_PostgresSQL.sql")
        }
	}
	
	void down() {
    }
	
}