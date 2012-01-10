class TreatmentAssignmentStudyIntervention extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

        createTable("treatment_assignment_study_interventions") { t ->
            t.addColumn("treatment_assignment_id", "integer")
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
        };

        createTable("treatment_assignment_agents") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("study_agent_id", "integer")
        };

        createTable("treatment_assignment_devices") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("study_device_id", "integer")
        };

        createTable("treatment_assignment_other_interventions") { t ->
            t.addColumn("grid_id", "string");
            t.addVersionColumn();
            t.addColumn("study_other_intervention_id", "integer");
        };

        createTable("study_interventions_expected_aes") { t ->
            t.addColumn("grid_id", "string");
            t.addVersionColumn();
            t.addColumn("term_type", "string");
            t.addColumn("treatment_assignment_study_intervention_id", "integer");
            t.addColumn("term_id", "integer");
            t.addColumn("low_level_term_id", "integer");
            t.addColumn("expectedness_frequency", "float");
            t.addColumn("grade1frequency", "float");
            t.addColumn("grade2frequency", "float");
            t.addColumn("grade3frequency", "float");
            t.addColumn("grade4frequency", "float");
            t.addColumn("grade5frequency", "float");
        };
    }

    void down() {
        dropTable("study_interventions_expected_aes");
        dropTable("treatment_assignment_agents");
        dropTable("treatment_assignment_devices");
        dropTable("treatment_assignment_other_interventions");
        dropTable("treatment_assignment_study_interventions");
    }
}
