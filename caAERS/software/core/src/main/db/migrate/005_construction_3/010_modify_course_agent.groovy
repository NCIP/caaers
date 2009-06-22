class ModifyCourseAgent extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("course_agents", "last_administered_date", "date")
        addColumn("course_agents", "modified_dose_amount", "numeric")
        addColumn("course_agents", "modified_dose_units", "string")
        addColumn("course_agents", "modified_dose_route", "string")
    }

    void down() {
        dropColumn("course_agents", "last_administered_date")
        dropColumn("course_agents", "modified_dose_amount")
        dropColumn("course_agents", "modified_dose_units")
        dropColumn("course_agents", "modified_dose_route")
    }
}