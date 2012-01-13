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

        execute('ALTER TABLE treatment_assignment_study_interventions ADD CONSTRAINT fk_ta_id FOREIGN KEY (treatment_assignment_id) REFERENCES treatment_assignment (id)')
        execute('ALTER TABLE study_interventions_expected_aes ADD CONSTRAINT fk_tai_id FOREIGN KEY (treatment_assignment_study_intervention_id) REFERENCES treatment_assignment_study_interventions (id)')
        execute('ALTER TABLE treatment_assignment_agents ADD CONSTRAINT fk_tasi_agent_id FOREIGN KEY (study_agent_id) REFERENCES study_agents (id)')
        execute('ALTER TABLE treatment_assignment_agents ADD CONSTRAINT fk_tasiagent_id FOREIGN KEY (id) REFERENCES treatment_assignment_study_interventions (id)')
        execute('ALTER TABLE treatment_assignment_devices ADD CONSTRAINT fk_tasi_device_id FOREIGN KEY (study_device_id) REFERENCES study_devices (id)')
        execute('ALTER TABLE treatment_assignment_devices ADD CONSTRAINT fk_tasidevice_id FOREIGN KEY (id) REFERENCES treatment_assignment_study_interventions (id)')
        execute('ALTER TABLE treatment_assignment_other_interventions ADD CONSTRAINT fk_tasi_other_id FOREIGN KEY (study_other_intervention_id) REFERENCES other_interventions (id)')
        execute('ALTER TABLE treatment_assignment_other_interventions ADD CONSTRAINT fk_tasiother_id FOREIGN KEY (id) REFERENCES treatment_assignment_study_interventions (id)')

    }

    void down() {

        execute("ALTER TABLE study_interventions_expected_aes DROP CONSTRAINT fk_tai_id");
        execute("ALTER TABLE treatment_assignment_study_interventions DROP CONSTRAINT fk_ta_id");
        execute("ALTER TABLE treatment_assignment_agents DROP CONSTRAINT fk_tasi_agent_id");
        execute("ALTER TABLE treatment_assignment_agents DROP CONSTRAINT fk_tasiagent_id");
        execute("ALTER TABLE treatment_assignment_devices DROP CONSTRAINT fk_tasi_device_id");
        execute("ALTER TABLE treatment_assignment_devices DROP CONSTRAINT fk_tasidevice_id");
        execute("ALTER TABLE treatment_assignment_other_interventions DROP CONSTRAINT fk_tasi_other_id");
        execute("ALTER TABLE treatment_assignment_other_interventions DROP CONSTRAINT fk_tasiother_id");

        dropTable("study_interventions_expected_aes");
        dropTable("treatment_assignment_agents");
        dropTable("treatment_assignment_devices");
        dropTable("treatment_assignment_other_interventions");
        dropTable("treatment_assignment_study_interventions");

    }
}
