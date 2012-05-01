class AddFailedToIntegrationLogDetails extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("integration_log_details","failed","boolean");
    }

    void down() {
    	dropColumn("integration_log_details","failed");
    }
}