class FirstAttributions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("ae_cause_types") { t ->
            t.setIncludePrimaryKey(false)
            t.addColumn("code", "string", scale: 2, primaryKey: true)
            t.addColumn("name", "string", nullable: false)
        }
        insert("ae_cause_types", [code: "SA", name: "study agent"], primaryKey: false)

        createTable("ae_attributions") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("adverse_event_id", "integer", nullable: false)
            t.addColumn("attribution_code", "integer", nullable: false)
            t.addColumn("cause_type", "string", scale: 2, nullable: false)
            t.addColumn("cause_id", "integer", nullable: false)
        }
        execute("ALTER TABLE ae_attributions ADD CONSTRAINT fk_attrib_ae FOREIGN KEY (adverse_event_id) REFERENCES adverse_events")
        execute("ALTER TABLE ae_attributions ADD CONSTRAINT fk_attrib_cause_type FOREIGN KEY (cause_type) REFERENCES ae_cause_types")
        // cause_id references several different tables, so I don't think we can set a FK for it

        createTable("concomitant_medications") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("agent_id", "integer")
            t.addColumn("other", "string")
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }
        execute("ALTER TABLE concomitant_medications ADD CONSTRAINT fk_conmed_report FOREIGN KEY (report_id) REFERENCES ae_reports")
        execute("ALTER TABLE concomitant_medications ADD CONSTRAINT fk_conmed_agent FOREIGN KEY (agent_id) REFERENCES agents")
        insert("ae_cause_types", [code: "CM", name: "concomitant medication"], primaryKey: false)
    }

    void down() {
        dropTable("concomitant_medications")
        dropTable("ae_attributions")
        dropTable("ae_cause_types", primaryKey: false)
    }
}