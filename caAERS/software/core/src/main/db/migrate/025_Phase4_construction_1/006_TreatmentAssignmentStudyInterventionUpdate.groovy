class TreatmentAssignmentStudyInterventionUpdate extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

		createTable("ta_expected_ae_intervention") { t ->
			t.includePrimaryKey = false
			t.addColumn("ta_expected_id", "integer")
			t.addColumn("ta_agent_id", "integer")
		}
		execute('ALTER TABLE study_interventions_exp_aes DROP CONSTRAINT fk_tai_id')
		dropColumn("study_interventions_exp_aes","ta_study_intervention_id")
		addColumn("study_interventions_exp_aes","treatment_assignment_id", "integer", nullabe: false)
		execute('ALTER TABLE study_interventions_exp_aes ADD CONSTRAINT fk_tai_id FOREIGN KEY (treatment_assignment_id) REFERENCES treatment_assignment (id)')
		execute('ALTER TABLE ta_expected_ae_intervention ADD CONSTRAINT fk_teai_tae_id FOREIGN KEY (ta_expected_id) REFERENCES study_interventions_exp_aes (id)')
		execute('ALTER TABLE ta_expected_ae_intervention ADD CONSTRAINT fk_teai_tasa_id FOREIGN KEY (ta_agent_id) REFERENCES ta_agents (id)')
    }

    void down() {
        dropTable("ta_expected_ae_intervention");
        dropColumn("study_interventions_exp_aes","treatment_assignment_id")
		addColumn("study_interventions_exp_aes","ta_study_intervention_id")
    }
}
