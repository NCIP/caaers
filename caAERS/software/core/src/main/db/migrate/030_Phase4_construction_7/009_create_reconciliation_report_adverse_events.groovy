class CreateReconciledAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
     void up() {
       	createTable("reconciled_adverse_events") { t ->
            t.addColumn("version_id", "integer", nullable: false)
            t.addColumn("item_id", "integer")
            t.addColumn("report_id", "integer")
            t.addColumn("attribution_summary_code", "integer")
            t.addColumn("why_serious", "string")
            t.addColumn("start_date", "date")
            t.addColumn("end_date", "date")
            t.addColumn("verbatim", "string")
            t.addColumn("error_message", "string")
            t.addColumn("grade_code", "integer")
            t.addColumn("external_id", "string")
            t.addColumn("term_code", "string")
            t.addColumn("term_name", "string")
            t.addColumn("term_other_specify", "string")
            t.addColumn("system", "integer", nullable: false)
        }
        
         execute('ALTER TABLE reconciled_adverse_events ADD CONSTRAINT fk_recon_ae_recon_rep_id FOREIGN KEY (report_id) REFERENCES reconciliation_reports (id)')
    }

    void down() {
        dropTable("reconciled_adverse_events")
    }
}