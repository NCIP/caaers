class AddAgentAdministered extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("treatments", "inv_agent_adminstrd", "boolean")
    }

    void down() {
        dropColumn("treatments", "inv_agent_adminstrd")
    }
}