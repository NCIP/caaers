class MigrationBootstrap extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('caaers_bootstrap_log') { t ->
            t.addColumn('rundate', 'date', nullable:true)
            t.addColumn('operation_code','integer', nullable:true)
            t.addColumn('status_code','integer', nullable:true)
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
    }

    void down() {
        dropTable('caaers_bootstrap_log')
    }
}