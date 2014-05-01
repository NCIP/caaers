class CreateTableAdverseEventRecommendedReports extends edu.northwestern.bioinformatics.bering.Migration {
     void up() {
       	createTable("ae_recom_reports") { t ->
            t.addColumn("ae_reported", "boolean", nullable: false)
            t.addVersionColumn()
            t.addColumn("adverse_event_id", "integer", nullable: false)
            t.addColumn("report_definition_id", "integer", nullable: false)
            t.addColumn("created_date", "timestamp", nullable: false)
            t.addColumn("due_date", "timestamp", nullable: false)	
            t.addColumn("grid_id", "string")
        }
        //fk
         execute("alter table ae_recom_reports add constraint fk_ae_recom_rpts_ae_id FOREIGN KEY(adverse_event_id) references adverse_events(id)");
         execute("alter table ae_recom_reports add constraint fk_ae_recom_rpts_rpt_cal_tmplt_id FOREIGN KEY(report_definition_id) references REPORT_CALENDAR_TEMPLATES(id)");
    }

    void down() {
        dropTable("ae_recom_reports")
    }
}