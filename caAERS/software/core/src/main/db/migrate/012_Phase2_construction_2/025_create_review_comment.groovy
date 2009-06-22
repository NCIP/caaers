class CreateNotificationRecipient extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("wf_review_comments") { t ->
        	t.addColumn("grid_id", "string")
            t.addVersionColumn()
            t.addColumn("user_comment", "string")
            t.addColumn("created_date", "date")
            t.addColumn("auto_text", "string")
            t.addColumn("rp_id", "integer") 
            t.addColumn("report_id", "integer") 
            t.addColumn("list_index", "integer")
            t.addColumn("dtype", "string" , nullable:false)
        }
        execute('alter table wf_review_comments add constraint fk_wf_comments_rp_id foreign key (rp_id) references ae_reporting_periods (id)')
        execute('alter table wf_review_comments add constraint fk_wf_comments_ae_rp_id foreign key (report_id) references REPORT_SCHEDULES (id)')
    }

    void down() {
        dropTable("wf_notification_recipient")
    }
}