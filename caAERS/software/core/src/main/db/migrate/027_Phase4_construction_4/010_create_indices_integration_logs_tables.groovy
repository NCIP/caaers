class CreateIndiciesIntegrationLogsTables extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 		execute('CREATE INDEX intlogs_correlation_id_idx ON integration_logs (correlation_id)')
 		execute('CREATE INDEX intlogdetails_log_id_idx ON integration_log_details (log_id)')
 		
    }

    void down() {
        dropTable('DROP INDEX intlogs_correlation_id_idx')
        dropTable('DROP INDEX intlogdetails_log_id_idx')
        
    }
}