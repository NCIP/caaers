class CreateCsm extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            String fileName = "db/migrate/001_iteration_1/CsmUptOracleSQL_create.sql"
            File input = new File(fileName)
            String content = input.text
            execute(content)
        } else if (databaseMatches('postgresql')){
            String fileName = "db/migrate/001_iteration_1/CsmUptPostgreSQL_create.sql"
            File input = new File(fileName)
            String content = input.text
            execute(content)
        } else {
            String fileName = "db/migrate/001_iteration_1/CsmUptHsqldbSQL_create.sql"
            File input = new File(fileName)
            String content = input.text
            execute(content)
        }
    }
    
    void down() {
        if (databaseMatches('oracle')) {
            String fileName = "db/migrate/001_iteration_1/CSMUptOracleSQL_drop.sql"
            File input = new File(fileName)
            String content = input.text
            execute(content)
        } else if (databaseMatches('postgresql')) {
            File input = new File("db/migrate/001_iteration_1/CSMUptPostgreSQL_drop.sql")
            execute(input.text)        
        } else {
            File input = new File("db/migrate/001_iteration_1/CsmUptHsqldbSQL_drop.sql")
            execute(input.text)
        }
    }
}

