class CreatePriorTherapyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 
         createTable("prior_theray_agents") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("ae_prior_therapy_id", "integer", nullable: false)
            t.addColumn("agent_id", "integer")
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }
        execute("ALTER TABLE prior_theray_agents ADD CONSTRAINT fk_prior_therapy_therapies FOREIGN KEY (ae_prior_therapy_id) REFERENCES ae_prior_therapies")
        execute("ALTER TABLE prior_theray_agents ADD CONSTRAINT fk_prior_therapy_agents FOREIGN KEY (agent_id) REFERENCES agents")
        
    }

    void down() {
        dropTable('prior_theray_agents')
    }
}