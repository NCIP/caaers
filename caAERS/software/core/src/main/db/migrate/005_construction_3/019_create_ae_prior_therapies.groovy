class CreateAePriorTherapies extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("ae_prior_therapies") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("prior_therapy_id", "integer")
            t.addColumn("other", "string")
            t.addColumn('start_date', 'date', nullable:true)
            t.addColumn('end_date', 'date', nullable:true)
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }

        execute("ALTER TABLE ae_prior_therapies ADD CONSTRAINT fk_aept_report FOREIGN KEY (report_id) REFERENCES ae_reports")
        execute("ALTER TABLE ae_prior_therapies ADD CONSTRAINT fk_aept_prior_therapy FOREIGN KEY (prior_therapy_id) REFERENCES prior_therapies")
    }

    void down() {
        dropTable("ae_prior_therapies")
    }
}