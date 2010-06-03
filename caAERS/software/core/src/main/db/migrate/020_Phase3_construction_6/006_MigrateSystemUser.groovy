class MigrateSystemUser extends edu.northwestern.bioinformatics.bering.Migration {
	
	void up() {
		if (databaseMatches('oracle')) {
            external("MigrateSystemUser_Oracle.sql")
        } else if (databaseMatches('postgresql')){
            external("MigrateSystemUser_PostgresSQL.sql")
        }
	}
	
	void down(){
	}
}