class ModifyStudyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("study_agents","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("study_agents", "retired_indicator")
    }
}