class AddAeDetailsAndLabs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("ae_reports") { t ->
            t.addVersionColumn()
            t.addColumn("assignment_id", "integer", nullable: false)
        }

        // No real systems yet, so this is okay
        execute("DELETE FROM adverse_events")
        dropColumn("adverse_events", "assignment_id")
        addColumn("adverse_events", "report_id", "integer")
        execute("ALTER TABLE adverse_events ADD CONSTRAINT fk_adv_ev_report FOREIGN KEY (report_id) REFERENCES ae_reports");

        createTable("ae_labs") { t ->
            t.addVersionColumn()
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("list_index", "integer", nullable: false)
            t.addColumn("name", "string")
            t.addColumn("units", "string")
            t.addColumn("baseline_date", "date")
            t.addColumn("baseline_value", "string")
            t.addColumn("nadir_date", "date")
            t.addColumn("nadir_value", "string")
            t.addColumn("recovery_date", "date")
            t.addColumn("recovery_value", "string")
        }
        execute("ALTER TABLE ae_labs ADD CONSTRAINT fk_lab_report FOREIGN KEY (report_id) REFERENCES ae_reports");
    }

    void down() {
        dropTable("ae_labs")

        execute("DELETE FROM adverse_events")
        addColumn("adverse_events", "assignment_id", "integer")
        dropColumn("adverse_events", "report_id")

        dropTable("ae_reports")
    }
}