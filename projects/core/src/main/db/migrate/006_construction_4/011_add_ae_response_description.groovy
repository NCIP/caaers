class AddAeResponseDescription extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("ae_report_descriptions") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("event_description", "string")
            t.addColumn("present_status_code", "integer")
            t.addColumn("recovery_date", "date")
            t.addColumn("retreated", "boolean")
            t.addColumn("removed_from_protocol", "boolean")
            t.addColumn("date_removed_from_protocol", "date")
        }
    }

    void down() {
        dropTable("ae_report_descriptions")
    }
}