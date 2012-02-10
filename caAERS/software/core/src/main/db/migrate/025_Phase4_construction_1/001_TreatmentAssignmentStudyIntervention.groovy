class TreatmentAssignmentStudyIntervention extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

        createTable("ta_study_interventions") { t ->
            t.addColumn("treatment_assignment_id", "integer")
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
        };

        createTable("ta_agents") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("study_agent_id", "integer")
        };

        createTable("ta_devices") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("study_device_id", "integer")
        };

        createTable("ta_other_interventions") { t ->
            t.addColumn("grid_id", "string");
            t.addVersionColumn();
            t.addColumn("study_other_intervention_id", "integer");
        };

        createTable("study_interventions_exp_aes") { t ->
            t.addColumn("grid_id", "string");
            t.addVersionColumn();
            t.addColumn("term_type", "string");
            t.addColumn("ta_study_intervention_id", "integer");
            t.addColumn("term_id", "integer");
            t.addColumn("low_level_term_id", "integer");
            t.addColumn("expectedness_frequency", "float");
            t.addColumn("grade1frequency", "float");
            t.addColumn("grade2frequency", "float");
            t.addColumn("grade3frequency", "float");
            t.addColumn("grade4frequency", "float");
            t.addColumn("grade5frequency", "float");
        };

        execute('ALTER TABLE ta_study_interventions ADD CONSTRAINT fk_ta_id FOREIGN KEY (treatment_assignment_id) REFERENCES treatment_assignment (id)')
        execute('ALTER TABLE study_interventions_exp_aes ADD CONSTRAINT fk_tai_id FOREIGN KEY (ta_study_intervention_id) REFERENCES ta_study_interventions (id)')
        execute('ALTER TABLE ta_agents ADD CONSTRAINT fk_tasi_agent_id FOREIGN KEY (study_agent_id) REFERENCES study_agents (id)')
        execute('ALTER TABLE ta_agents ADD CONSTRAINT fk_tasiagent_id FOREIGN KEY (id) REFERENCES ta_study_interventions (id)')
        execute('ALTER TABLE ta_devices ADD CONSTRAINT fk_tasi_device_id FOREIGN KEY (study_device_id) REFERENCES study_devices (id)')
        execute('ALTER TABLE ta_devices ADD CONSTRAINT fk_tasidevice_id FOREIGN KEY (id) REFERENCES ta_study_interventions (id)')
        execute('ALTER TABLE ta_other_interventions ADD CONSTRAINT fk_tasi_other_id FOREIGN KEY (study_other_intervention_id) REFERENCES other_interventions (id)')
        execute('ALTER TABLE ta_other_interventions ADD CONSTRAINT fk_tasiother_id FOREIGN KEY (id) REFERENCES ta_study_interventions (id)')

    }

    void down() {


        dropTable("study_interventions_exp_aes");
        dropTable("ta_agents");
        dropTable("ta_devices");
        dropTable("ta_other_interventions");
        dropTable("ta_study_interventions");

    }
}
