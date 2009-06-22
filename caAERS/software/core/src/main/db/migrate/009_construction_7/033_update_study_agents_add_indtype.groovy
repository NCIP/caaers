class ModifyStudyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("study_agents","ind_type", 'integer');
          

    }

    void down() {
		
    		dropColumn("study_agents","ind_type")
    }
    
}