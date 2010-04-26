class CreateReportingPeriodIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("reportingperiod_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("reportingperiod_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table reportingperiod_index add constraint fk_rper_index_rper_id foreign key (reportingperiod_id) references ae_reporting_periods (id)')
    }

    void down() {
        dropTable("reportingperiod_index")
    }
}