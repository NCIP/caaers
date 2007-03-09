class MigrateCsm32 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            File input = new File("src/main/db/migrate/003_iteration_3/csm-3.2-oracle-up.sql")
            execute(input.text)
        } else if (databaseMatches('postgresql')){
            File input = new File("src/main/db/migrate/003_iteration_3/csm-3.2-postgresql-up.sql")
            execute(input.text)
        } else if (databaseMatches('hsqldb')){
            File input = new File("src/main/db/migrate/003_iteration_3/csm-3.2-hsqldb-up.sql")
            execute(input.text)
        } else {
            File input = new File("src/main/db/migrate/003_iteration_3/csm-3.2-mysql-up.sql")
            execute(input.text)
        }
    }
    
    void down() {
        if (databaseMatches('oracle')) {
            File input = new File("src/main/db/migrate/003_iteration_3/csm-3.2-oracle-down.sql")
            execute(input.text)
        } else if (databaseMatches('postgresql')) {
            File input = new File("src/main/db/migrate/003_iteration_3/csm-3.2-postgresql-down.sql")
            execute(input.text)        
        } else if (databaseMatches('hsqldb')) {
            File input = new File("src/main/db/migrate/003_iteration_3/csm-3.2-hsqldb-down.sql")
            execute(input.text)
        } else {
            File input = new File("src/main/db/migrate/003_iteration_3/csm-3.2-mysql-down.sql")
            execute(input.text)
        }
    }
}