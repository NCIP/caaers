class SetupjBPM extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            external("jbpm_jpdl_oracle.sql")
        } else if (databaseMatches('postgresql')){
            external("jbpm_jpdl_postgresql.sql")
        } else {
            external("jbpm_jpdl_hsqldb.sql")
        }
    }
    
    void down() {
        if (databaseMatches('oracle')) {
            external("jbpm_jpdl_oracle_drop.sql")
        } else if (databaseMatches('postgresql')) {
            external("jbpm_jpdl_postgresql_drop.sql")
        } else {
            external("jbpm_jpdl_hsqldb_drop.sql")
        }
    }
}
