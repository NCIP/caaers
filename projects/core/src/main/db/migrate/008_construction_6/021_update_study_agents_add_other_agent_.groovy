class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("study_agents","other_agent", 'string');
            setNullable("study_agents", "agent_id", true);
            

    }

    void down() {
			setNullable("study_agents", "agent_id", false);
    		dropColumn("study_agents","other_agent")
    }
    
}