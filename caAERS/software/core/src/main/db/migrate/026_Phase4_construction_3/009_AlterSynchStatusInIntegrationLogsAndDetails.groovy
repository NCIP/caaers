class AlterTableIntegrationLogsAndDetails extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	dropColumn("integration_logs","synch_status");
    	dropColumn("integration_log_details","synch_status");
    	addColumn("integration_logs","synch_status","string");
    	addColumn("integration_log_details","synch_status","string");
    }

    void down() {
    	dropColumn("integration_logs","synch_status");
    	dropColumn("integration_log_details","synch_status");
    	addColumn("integration_logs","synch_status","integer");
    	addColumn("integration_log_details","synch_status","integer");
    }
}