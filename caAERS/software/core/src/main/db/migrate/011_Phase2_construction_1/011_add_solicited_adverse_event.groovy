class AddSolicited extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		addColumn("adverse_events", "solicited", "boolean");
		addColumn("adverse_events", "reporting_period_id", "integer");
        execute('ALTER TABLE adverse_events ADD CONSTRAINT fk_reporting_period_id FOREIGN KEY(reporting_period_id) REFERENCES ae_reporting_periods');
	}
	
	void down(){
		dropColumn("adverse_events", "solicited");
		dropColumn("adverse_events", "reporting_period_id");
	}
}