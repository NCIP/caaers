class CreateReconciliationRptReportAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
     void up() {
       	createTable("recon_rpt_adv_events") { t ->
            t.addColumn("version_id", "integer", nullable: false)
            t.addColumn("item_id", "integer")
            t.addColumn("recon_rep_prd_id", "integer")
            t.addColumn("attribution", "string")
            t.addColumn("why_serious", "string")
            t.addColumn("start_date", "date")
            t.addColumn("end_date", "date")
            t.addColumn("verbatim", "string")
            t.addColumn("error_message", "string")
            t.addColumn("grade", "integer", nullable: false)
            t.addColumn("system", "integer", nullable: false)
        }
        
         execute('ALTER TABLE recon_rpt_adv_events ADD CONSTRAINT fk_rec_rpt_ae_recon_rep_prd_id FOREIGN KEY (recon_rep_prd_id) REFERENCES recon_rprt_rep_periods (id)')
    }

    void down() {
        dropTable("recon_rpt_adv_events")
    }
}