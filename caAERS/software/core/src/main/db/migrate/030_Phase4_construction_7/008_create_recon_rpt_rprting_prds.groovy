class CreateReconciliationRptReportingPeriods extends edu.northwestern.bioinformatics.bering.Migration {
     void up() {
       	createTable("recon_rprt_rep_periods") { t ->
            t.addColumn("version_id", "integer", nullable: false)
            t.addColumn("reporting_period_id", "integer")
            t.addColumn("created_date", "date", nullable:false)
            t.addColumn("updated_date", "date")
            t.addColumn("reviewed_by", "string", nullable: false)
        }
        
        execute('ALTER TABLE recon_rprt_rep_periods ADD CONSTRAINT fk_rec_rpt_prd_ae_rep_prd_id FOREIGN KEY (reporting_period_id) REFERENCES ae_reporting_periods (id)')
    }

    void down() {
        dropTable("recon_rprt_rep_periods")
    }
}