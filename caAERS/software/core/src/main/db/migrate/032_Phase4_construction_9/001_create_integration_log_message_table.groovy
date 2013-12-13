class CreateIntegrationLogMessageTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       	createTable("integration_log_message") { t ->
            t.addColumn("combo_message_id", "string", nullable: false)
            t.addVersionColumn()
            t.addColumn("message", "string", nullable: false)
            t.addColumn("stage", "string", nullable: false)
            t.addColumn("log_id", "integer", nullable: false)
        }
        //fk
        execute("alter table integration_log_message add constraint fk_log_message_id FOREIGN KEY(log_id) references integration_logs(id)");
        
    }

    void down() {
        dropTable("integration_log_message")
    }
}