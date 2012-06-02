class UpdateSpaPriorTherapyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("spa_prior_therapy_agents","agent_id","integer");
        execute("alter table spa_prior_therapy_agents add constraint fk_spa_pt_agents_agent_id FOREIGN KEY(agent_id) references agents(ID)");

    }

    void down() {
        dropColumn("spa_prior_therapy_agents","agent_id");
    }
}