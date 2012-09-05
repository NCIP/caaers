class CreateExternalAdverseEventsReportingPeriodsTable extends edu.northwestern.bioinformatics.bering.Migration {
   void up() {
       	createTable("ext_ae_reporting_prds") { t ->
            t.addColumn("version_id", "integer", nullable: false)
            t.addColumn("description", "string")
            t.addColumn("cycle_number", "string")
            t.addColumn("workflow_id", "integer")
            t.addColumn("review_status", "string", nullable: false)
            t.addColumn("treatment_assignment_code", "string", nullable: false)
            t.addColumn("treatment_assignment_desc", "integer", nullable: false)
            t.addColumn("start_date", "date", nullable: false)
            t.addColumn("end_date", "date")
            t.addColumn("name", "string", nullable: false)
            t.addColumn("external_id", "string", nullable: false)
            t.addColumn("assignment_id", "integer", nullable: false)
        }
        
         execute('ALTER TABLE ext_ae_reporting_prds ADD CONSTRAINT fk_ext_rprt_per_prt_assgnmnts_id FOREIGN KEY (assignment_id) REFERENCES participant_assignments (id)')
    }

    void down() {
        dropTable("ext_ae_reporting_prds")
    }
}