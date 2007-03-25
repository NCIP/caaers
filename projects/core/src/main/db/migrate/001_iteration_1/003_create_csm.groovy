class CreateCsm extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            external("CsmUptOracleSQL_create.sql")
        } else if (databaseMatches('postgresql')){
            external("CsmUptPostgreSQL_create.sql")
        } else {
            external("CsmUptHsqldbSQL_create.sql")
        }
    }
    
    void down() {
        if (databaseMatches('oracle')) {
            external("CSMUptOracleSQL_drop.sql")
        } else if (databaseMatches('postgresql')) {
            external("CSMUptPostgreSQL_drop.sql")
        } else {
            external("CsmUptHsqldbSQL_drop.sql")
        }
    }
}

