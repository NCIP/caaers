class ModifyCourseAgent extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("course_agents", "lot_number", "string")
        addColumn("course_agents", "formulation", "string")
    }

    void down() {
        dropColumn("course_agents", "lot_number")
        dropColumn("course_agents", "formulation")
    }
}