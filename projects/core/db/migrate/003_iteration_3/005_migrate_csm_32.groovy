class MigrateCsm32 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            String fileName = "db/migrate/003_iteration_3/csm-3.2-oracle-up.sql"
            File input = new File(fileName)
            String content = input.text
            execute(content)
        } else if (databaseMatches('postgresql')){
            String fileName = "db/migrate/003_iteration_3/csm-3.2-postgresql-up.sql"
            File input = new File(fileName)
            String content = input.text
            execute(content)
        } else {
            String fileName = "db/migrate/003_iteration_3/csm-3.2-mysql-up.sql"
            File input = new File(fileName)
            String content = input.text
            execute(content)
        }
    }
    
    void down() {
        if (databaseMatches('oracle')) {
            String fileName = "db/migrate/003_iteration_3/csm-3.2-oracle-down.sql"
            File input = new File(fileName)
            String content = input.text
            execute(content)
        } else if (databaseMatches('postgresql')) {
            File input = new File("db/migrate/003_iteration_3/csm-3.2-postgresql-down.sql")
            execute(input.text)        
        } else {
            File input = new File("db/migrate/003_iteration_3/csm-3.2-mysql-down.sql")
            execute(input.text)
        }
    }
}