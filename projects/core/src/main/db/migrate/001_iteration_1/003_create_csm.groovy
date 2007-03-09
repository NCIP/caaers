class CreateCsm extends edu.northwestern.bioinformatics.bering.Migration {
    private static String dir = "src/main/db/migrate/001_iteration_1/";

    void up() {
        if (databaseMatches('oracle')) {
            File input = new File(dir + "CsmUptOracleSQL_create.sql")
            execute(input.text)
        } else if (databaseMatches('postgresql')){
            File input = new File(dir + "CsmUptPostgreSQL_create.sql")
            execute(input.text)
        } else {
            File input = new File(dir + "CsmUptHsqldbSQL_create.sql")
            execute(input.text)
        }
    }
    
    void down() {
        if (databaseMatches('oracle')) {
            File input = new File(dir + "CSMUptOracleSQL_drop.sql")
            execute(input.text)
        } else if (databaseMatches('postgresql')) {
            File input = new File(dir + "CSMUptPostgreSQL_drop.sql")
            execute(input.text)        
        } else {
            File input = new File(dir + "CsmUptHsqldbSQL_drop.sql")
            execute(input.text)
        }
    }
}

