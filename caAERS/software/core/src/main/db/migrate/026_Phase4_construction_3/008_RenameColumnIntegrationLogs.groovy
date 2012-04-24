class RenameColumnIntegrationLogs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	execute("alter table integration_logs rename column corelation_id to correlation_id");
    }

    void down() {
    	execute("alter table integration_logs rename column correlation_id to corelation_id");
    }
}