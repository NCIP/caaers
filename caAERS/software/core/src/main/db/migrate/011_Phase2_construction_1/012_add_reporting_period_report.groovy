class AddReportReportingPeriod extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		addColumn("ae_reports", "reporting_period_id", "integer");
        execute('ALTER TABLE ae_reports ADD CONSTRAINT fk_ae_reports_rp_id FOREIGN KEY(reporting_period_id) REFERENCES ae_reporting_periods');
	}
	
	void down(){
		dropColumn("ae_reports", "reporting_period_id");
	}
}