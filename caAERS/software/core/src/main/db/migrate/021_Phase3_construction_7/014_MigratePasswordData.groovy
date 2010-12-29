class MigratePasswordData extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
    	if (databaseMatches('oracle')) {
            external("migrate_password_data_oracle.sql")
        } else if (databaseMatches('postgresql')){
            external("migrate_password_data_postgres.sql")
        }
    }

    void down() {
    }
}