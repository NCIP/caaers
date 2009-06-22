class CreateRadiationInterventions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
    	createTable("radiation_interventions") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("treatment_arm", "string")
            t.addColumn("description", "string")
            t.addColumn("radiation_administration_code", "integer")
            t.addColumn("dosage", "string")
            t.addColumn("dosage_unit", "string")
            t.addColumn("last_treatment_date","date")
            t.addColumn("fraction_number", "string")
            t.addColumn("days_elapsed", "string")
            t.addColumn("adjustment", "string")
        }
    }

    void down() {
        dropTable('radiation_interventions')
    }
}