class AlterTableIntegrationLogsAndDetails extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	execute("alter table integration_logs rename column description to notes");
    	execute("alter table integration_logs rename column stage_id to synch_status");
    	execute("alter table integration_log_details rename column description to outcome");
	    addColumn("integration_log_details","synch_status","integer");
    }

    void down() {
    	dropColumn("integration_log_details","stage_id");
    	execute("alter table integration_logs rename column notes to description");
    	execute("alter table integration_logs rename column synch_status to stage_id");
    	execute("alter table integration_log_details rename outcome error_message to description");
    }
}