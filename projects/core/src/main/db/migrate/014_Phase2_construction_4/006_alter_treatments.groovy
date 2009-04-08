class AddAgentAdministered extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("treatments", "investigational_agent_administered", "boolean")
    }

    void down() {
        dropColumn("treatments", "investigational_agent_administered")
    }
}