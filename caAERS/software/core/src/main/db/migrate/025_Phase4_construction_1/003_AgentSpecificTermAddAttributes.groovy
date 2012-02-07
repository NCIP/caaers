class AgentSpecificTermAddAttributes extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

		addColumn('agent_terms', "expectedness_frequency", "float");
		addColumn('agent_terms', "grade1frequency", "float");
		addColumn('agent_terms', "grade2frequency", "float");
		addColumn('agent_terms', "grade3frequency", "float");
		addColumn('agent_terms', "grade4frequency", "float");
		addColumn('agent_terms', "grade5frequency", "float");
		addColumn('agent_terms', "expected", "boolean");
		
    }

    void down() {

        dropColumn('agent_terms', "expectedness_frequency");
		dropColumn('agent_terms', "grade1frequency");
		dropColumn('agent_terms', "grade2frequency");
		dropColumn('agent_terms', "grade3frequency");
		dropColumn('agent_terms', "grade4frequency");
		dropColumn('agent_terms', "grade5frequency");
		dropColumn('agent_terms', "expected");

    }
}
