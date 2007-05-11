class MigrateCsm32 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            external("csm-3.2-oracle-up.sql")
        } else if (databaseMatches('postgresql')){
            external("csm-3.2-postgresql-up.sql")
        } else if (databaseMatches('hsqldb')){
            external("csm-3.2-hsqldb-up.sql")
        } else {
            external("csm-3.2-mysql-up.sql")
        }
    }
    
    void down() {
        if (databaseMatches('oracle')) {
            external("csm-3.2-oracle-down.sql")
        } else if (databaseMatches('postgresql')) {
            external("csm-3.2-postgresql-down.sql")
        } else if (databaseMatches('hsqldb')) {
            external("csm-3.2-hsqldb-down.sql")
        } else {
            external("csm-3.2-mysql-down.sql")
        }
    }
}