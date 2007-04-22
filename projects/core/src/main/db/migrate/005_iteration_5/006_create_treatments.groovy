class CreateTreatments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("treatments") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("first_course_date", "date")
            t.addColumn("adverse_event_course_date", "date")
            t.addColumn("adverse_event_course_number", "integer")
        }

        createTable("course_agents") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("treatment_id", "integer", nullable: false)
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
            t.addColumn("study_agent_id", "integer")
            t.addColumn("administration_delay_minutes", "numeric")
            t.addColumn("dose_amount", "numeric")
            t.addColumn("dose_units", "string")
            t.addColumn("dose_route", "string")
            t.addColumn("total_dose_this_course", "numeric")
            t.addColumn("duration_and_schedule", "string")
        }
    }

    void down() {
        dropTable("course_agents")
        dropTable("treatments")
    }
}