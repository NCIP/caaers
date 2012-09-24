class CreateReconciliationReports extends edu.northwestern.bioinformatics.bering.Migration {
     void up() {
       	createTable("reconciliation_reports") { t ->
            t.addColumn("version", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("reporting_period_id", "integer")
            t.addColumn("created_date", "date", nullable:false)
            t.addColumn("updated_date", "date")
            t.addColumn("reviewed_by", "string", nullable: false)
        }
        
        execute('ALTER TABLE reconciliation_reports ADD CONSTRAINT fk_recon_reports_rp_id FOREIGN KEY (reporting_period_id) REFERENCES ae_reporting_periods (id)')
    }

    void down() {
        dropTable("reconciliation_reports")
    }
}