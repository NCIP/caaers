class UpdateCourseAgent extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("course_agents", "agent_adjustment_code", "integer", limit: 127)
    }

    void down() {
        dropColumn("course_agents", "agent_adjustment_code")

    }
}