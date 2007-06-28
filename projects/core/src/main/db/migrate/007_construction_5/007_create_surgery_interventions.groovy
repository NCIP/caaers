class CreateSurgeryInterventions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
    	createTable("surgery_interventions") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("treatment_arm", "string")
            t.addColumn("description", "string")
            t.addColumn("anatomic_site_id", "integer")
            t.addColumn("intervention_date","date")
        }
    }

    void down() {
        dropTable('surgery_interventions')
    }
}