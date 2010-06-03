class CSM32TO42Migrate extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
    	if (databaseMatches('oracle')) {
            external("CSM_32TO42_Migration_Oracle.sql")
        } else if (databaseMatches('postgresql')){
            external("CSM_32TO42_Migration_PostgresSQL.sql")
        }
    }

    void down() {
    }
}