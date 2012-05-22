class CreateIndiciesIntegrationLogs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 		execute('CREATE INDEX intlogs_logged_on_idx ON integration_logs (logged_on)')
 		execute('CREATE INDEX intlogs_operation_idx ON integration_logs (operation)')
 		execute('CREATE INDEX intlogs_entity_idx ON integration_logs (entity)')
 		
    }

    void down() {
        dropTable('DROP INDEX intlogs_entity_idx')
        dropTable('DROP INDEX intlogs_operation_idx')
        dropTable('DROP INDEX intlogs_logged_on_idx')
        
    }
}