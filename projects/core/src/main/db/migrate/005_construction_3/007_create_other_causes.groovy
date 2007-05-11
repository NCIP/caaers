class CreateOtherCauses extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("other_causes") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
            t.addColumn("cause_text", "string")
        }

        execute("ALTER TABLE other_causes ADD CONSTRAINT fk_other_cause_report FOREIGN KEY (report_id) REFERENCES ae_reports")
    }

    void down() {
        dropTable("other_causes")
    }
}