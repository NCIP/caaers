class CreateReportIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("report_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("role_code", "integer", defaultValue: 0)
            t.addColumn("grid_id", "string")
        }
         execute('alter table report_index add constraint fk_report_index_rep_id foreign key (report_id) references REPORT_SCHEDULES (id)')
    }

    void down() {
        dropTable("report_index")
    }
}

