class updatePriorTherapyAgents  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	renameTable('prior_theray_agents','prior_therapy_agents');
    	execute("ALTER TABLE prior_therapy_agents DROP CONSTRAINT fk_prior_therapy_agents");
    	dropColumn("prior_therapy_agents", "agent_id")
    	addColumn("prior_therapy_agents", "chemo_agent_id","integer")
    	execute('ALTER TABLE prior_therapy_agents ADD CONSTRAINT fk_prior_therapy_chemo_agents FOREIGN KEY (chemo_agent_id) REFERENCES chemo_agents')
    }


    public void down(){
    	renameTable('prior_therapy_agents','prior_theray_agents');
    	execute("ALTER TABLE prior_theray_agents DROP CONSTRAINT fk_prior_therapy_chemo_agents");
    	dropColumn("prior_theray_agents", "chemo_agent_id")
    	addColumn("prior_theray_agents", "agent_id","integer")
    	execute('ALTER TABLE prior_theray_agents ADD CONSTRAINT fk_prior_therapy_agents FOREIGN KEY (agent_id) REFERENCES agents')
    }

  }
