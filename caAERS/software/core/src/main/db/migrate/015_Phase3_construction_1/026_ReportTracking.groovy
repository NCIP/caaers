class CreateReportTracking extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
        createTable("report_tracking_status") { t ->
            t.addVersionColumn()
            t.addColumn("status", "boolean", nullable:false)
            t.addColumn("status_message", "string")
			t.addColumn("recorded_time", "date", nullable:false)
			t.addColumn('grid_id' , 'string' , nullable:true)
        }
         
        createTable("report_tracking") { t ->
            t.addVersionColumn()
            t.addColumn("sub_trk_id", "integer")
            t.addColumn("xml_trk_id", "integer")
            t.addColumn("attachment_trk_id", "integer")
            t.addColumn("emailn_trk_id", "integer")
            t.addColumn("esbcn_trk_id", "integer")
            t.addColumn("systemcn_trk_id", "integer")
            t.addColumn("syssub_trk_id", "integer")
            t.addColumn("response_trk_id", "integer")
            t.addColumn("emailsubn_trk_id", "integer")
            t.addColumn("grid_id" , "string" , nullable:true)
            t.addColumn("report_version_id", "integer")
            t.addColumn("attempt_number","integer")
        }  
        execute('alter table report_tracking add constraint fk_trk_sts_id0 foreign key (xml_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_trk_sts_id1 foreign key (attachment_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_trk_sts_id2 foreign key (esbcn_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_trk_sts_id3 foreign key (systemcn_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_trk_sts_id4 foreign key (emailn_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_trk_sts_id5 foreign key (emailsubn_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_trk_sts_id6 foreign key (response_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_trk_sts_id7 foreign key (sub_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_trk_sts_id9 foreign key (syssub_trk_id) references report_tracking_status (id)')
        execute('alter table report_tracking add constraint fk_report_id11 foreign key (report_version_id) references report_versions (id)')
     }

    void down() {
        dropTable("report_tracking_status")
        dropTable("report_tracking")

    }
}