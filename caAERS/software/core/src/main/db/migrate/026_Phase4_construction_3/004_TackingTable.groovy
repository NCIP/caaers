class CreateTrackingTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('integration_logs') { t ->
            t.addColumn('logged_on' , 'timestamp' , nullable:false);
            t.addColumn('corelation_id', 'string', nullable:false)
            t.addColumn('stage_id','integer', nullable:false)
            t.addColumn('description','string', nullable:true)
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }

        if (databaseMatches('postgres')) {
            execute("ALTER TABLE integration_logs ALTER COLUMN logged_on SET DEFAULT now()");
        }

        if (databaseMatches('oracle')) {
            execute("ALTER TABLE integration_logs MODIFY logged_on SET DEFAULT sysdate");
        }

        createTable('integration_log_details') { t ->
            t.addColumn('log_id', 'integer', nullable:false)
            t.addColumn('corelation_id','integer', nullable:true)
            t.addColumn('business_id','string', nullable:true)
            t.addColumn('description','string', nullable:true)
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        //fk
        execute("alter table integration_log_details add constraint fk_log_details_id FOREIGN KEY(log_id) references integration_logs(id)");


    }

    void down() {
       dropTable("integration_log_details")
       dropTable("integration_logs")
    }
}