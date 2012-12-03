class ModifyCourseAgent extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("course_agents", "first_administered_date", "date")
    }

    void down() {
        dropColumn("course_agents", "first_administered_date")
    }
}