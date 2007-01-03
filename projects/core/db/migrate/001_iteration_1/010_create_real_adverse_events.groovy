class CreateRealAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropTable("adverse_events")

        createTable("adverse_events") { t->
            t.addVersionColumn()
            t.addColumn("detection_date", "timestamp", nullable: false)
            t.addColumn("assignment_id", "integer", nullable: false)
            t.addColumn("ctc_version_id", "integer")
            t.addColumn("ctc_category_id", "integer")
            t.addColumn("term", "string")
            t.addColumn("details_for_other", "string")
            t.addColumn("grade_code", "integer")
            t.addColumn("attribution_code", "integer")
            t.addColumn("hospitalization", "boolean")
        }

        execute("ALTER TABLE adverse_events ADD CONSTRAINT fk_adv_ev_ctc_ver FOREIGN KEY (ctc_version_id) REFERENCES ctc_versions");
        execute("ALTER TABLE adverse_events ADD CONSTRAINT fk_adv_ev_ctc_cat FOREIGN KEY (ctc_category_id) REFERENCES ctc_categories");
    }

    void down() {
        dropTable("adverse_events")
        // From 1|1
        createTable("adverse_events") { t ->
            t.addVersionColumn()
            t.addColumn("time", "timestamp", nullable: false)
        }
    }
}