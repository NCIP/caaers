class UpdateSpaPriorTherapyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("prior_therapy_agents","agent_id","integer");
        execute("alter table prior_therapy_agents add constraint fk_sae_pt_agents_agent_id FOREIGN KEY(agent_id) references agents(ID)");

    }

    void down() {
        dropColumn("prior_therapy_agents","agent_id");
    }
}