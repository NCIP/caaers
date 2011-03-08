class CreateAEBehavioralInterventions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	createTable("ae_behavioral_interventions") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("description", "string")
            t.addColumn("study_intervention_id", "integer")
            t.addColumn('list_index','integer', nullable:true)
        }
    	createTable("ae_genetic_interventions") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("description", "string")
            t.addColumn("study_intervention_id", "integer")
            t.addColumn('list_index','integer', nullable:true)
        }
    	createTable("ae_biological_interventions") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("description", "string")
            t.addColumn("study_intervention_id", "integer")
            t.addColumn('list_index','integer', nullable:true)
        }
    	createTable("ae_dietary_interventions") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("description", "string")
            t.addColumn("study_intervention_id", "integer")
            t.addColumn('list_index','integer', nullable:true)
        }
    	createTable("ae_other_interventions") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("description", "string")
            t.addColumn("study_intervention_id", "integer")
            t.addColumn('list_index','integer', nullable:true)
        }
    }

    void down() {
        dropTable('ae_behavioral_interventions')
        dropTable('ae_genetic_interventions')
        dropTable('ae_biological_interventions')
        dropTable('ae_dietary_interventions')
        dropTable('ae_other_interventions')
    }
}