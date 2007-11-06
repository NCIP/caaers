class UpdateStudyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("study_agents","part_of_leadind", "boolean", nullable:true);
           
            

    }

    void down() {
			
    		dropColumn("study_agents","part_of_leadind")
    }
    
}