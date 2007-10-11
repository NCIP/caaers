class SubmitterTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_report_people", "report_schedule_id", "integer")
        execute("ALTER TABLE ae_report_people ADD CONSTRAINT fk_people_aers FOREIGN KEY (report_schedule_id) REFERENCES report_schedules")
        addColumn("report_schedules", "physician_signoff", "boolean")
        
    }

    void down() {
        dropColumn("report_schedules", "physician_signoff")
        dropColumn("ae_report_people", "report_schedule_id")
    }
}