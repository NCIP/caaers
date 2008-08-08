class CreateSpaPriorTherapyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	createTable("spa_prior_therapy_agents") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("spa_prior_therapy_id", "integer", nullable: false)
            t.addColumn("chemo_agent_id", "integer")
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }
        execute("ALTER TABLE spa_prior_therapy_agents ADD CONSTRAINT fk_spa_pt_therapies FOREIGN KEY (spa_prior_therapy_id) REFERENCES spa_prior_therapies")
        execute('ALTER TABLE spa_prior_therapy_agents ADD CONSTRAINT fk_spa_pt_chemo_agents FOREIGN KEY (chemo_agent_id) REFERENCES chemo_agents')
    }
    
    void down(){
    	dropTable('spa_prior_therapy_agents')
    }
}